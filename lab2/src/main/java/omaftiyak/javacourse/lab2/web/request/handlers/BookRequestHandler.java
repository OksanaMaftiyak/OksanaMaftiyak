package omaftiyak.javacourse.lab2.web.request.handlers;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.ColumnOrdering;
import omaftiyak.javacourse.lab2.model.Order;
import omaftiyak.javacourse.lab2.model.OrderingInfo;
import omaftiyak.javacourse.lab2.service.BookService;
import omaftiyak.javacourse.lab2.validator.BookValidator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookRequestHandler implements RequestHandler {

    private static final Pattern BOOK_HOME_PATH_PATTERN = Pattern.compile("/library/(\\d+)/?");

    private static final Pattern LIBRARY_BOOKS_JSON_PATTERN = Pattern.compile("/library.json/(\\d+)/book.json");
    private static final int LIBRARY_ID_GROUP = 1;

    private static final Pattern BOOK_JSON_PATTERN = Pattern.compile("/library.json/(\\d+)/book.json/(\\d+)");
    private static final int BOOK_ID_GROUP = 2;

    private static final Pattern SEARCH_BOOK_JSON_PATTERN = Pattern.compile("/library.json/(\\d+)/book.json/search");

    private static final String BOOKS_PAGE_HTML = "/files/html/books.html";

    private BookService bookService = new BookService();
    private BookValidator bookValidator = new BookValidator();

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String requestedPath = request.getPathInfo();
        if (GET.equals(request.getMethod()) && BOOK_HOME_PATH_PATTERN.matcher(requestedPath).matches()) {
            Utils.sendFileContent(BOOKS_PAGE_HTML, request, response);
            return true;
        } else {

            Matcher libraryMatcher = LIBRARY_BOOKS_JSON_PATTERN.matcher(requestedPath);
            if (libraryMatcher.matches()) {
                Long libraryId = Long.valueOf(libraryMatcher.group(LIBRARY_ID_GROUP));
                if (GET.equals(request.getMethod())) {
                    sendAllBooks(libraryId, request, response);
                    return true;
                } else if (POST.equals(request.getMethod())) {
                    createBook(request, response, libraryId);
                    return true;
                }
            }

            Matcher bookMatcher = BOOK_JSON_PATTERN.matcher(requestedPath);
            if (bookMatcher.matches()) {

                Long libraryId = Long.valueOf(bookMatcher.group(LIBRARY_ID_GROUP));
                Long bookId = Long.valueOf(bookMatcher.group(BOOK_ID_GROUP));
                if (GET.equals(request.getMethod())) {
                    Utils.writeJson(response, bookService.findBookById(libraryId, bookId));
                    return true;
                } else if (PUT.equals(request.getMethod())) {
                    updateBook(request, response, libraryId, bookId);
                    return true;
                } else if (DELETE.equals(request.getMethod())) {
                    bookService.delete(libraryId, bookId);
                    Utils.writeJson(response, "OK");
                    return true;
                }

            }

            Matcher searchMatcher = SEARCH_BOOK_JSON_PATTERN.matcher(requestedPath);
            if (GET.equals(request.getMethod()) && searchMatcher.matches()) {
                handleSearch(Long.valueOf(searchMatcher.group(LIBRARY_ID_GROUP)), request, response);
                return true;
            }

        }
        return false;
    }

    private void handleSearch(Long libraryId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String searchField = request.getParameter("searchField");
        String query = request.getParameter("query");
        List<Book> books = Collections.EMPTY_LIST;
        OrderingInfo ordering = extractOrderingInfo(request);
        switch (searchField) {
            case "bookTitle":
                books = bookService.selectBooksByTitle(libraryId, query, ordering);
                break;
            case "yearPublication":
                books = bookService.findBooksByYear(libraryId, Integer.parseInt(query), ordering);
                break;
            case "author":
                books = bookService.findBooksByAuthor(libraryId, query, ordering);
                break;
        }
        Utils.writeJson(response, books);
    }

    private void sendAllBooks(Long libraryId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utils.writeJson(response, bookService.getAllBooksForLibrary(libraryId, extractOrderingInfo(request)));
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response, Long libraryId) throws IOException {
        Book book = Utils.readJson(request, Book.class);
        try {
            bookValidator.validate(book.getParts());
            bookService.addBook(libraryId, book);
            Utils.writeJson(response, book);
        } catch (ValidatorException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            handleValidationError(response, e);
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response, Long libraryId, Long bookId) throws IOException {
        Book book = Utils.readJson(request, Book.class);
        book.setId(bookId);
        try {
            bookValidator.validate(book.getParts());
            bookService.update(libraryId, book);
            Utils.writeJson(response, book);
        } catch (ValidatorException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            handleValidationError(response, e);
        }
    }

    private OrderingInfo extractOrderingInfo(HttpServletRequest request) {
        String ordering = request.getParameter("ordering");
        if (ordering == null || ordering.trim().isEmpty()) {
            return null;
        }
        LinkedList<ColumnOrdering> columnOrderings = new LinkedList<>();
        for (String columnOrdering : ordering.split(";")) {
            String[] parts = columnOrdering.split(":");
            String column = parts[0];
            Order order = Order.ASC;
            if (parts.length == 2) {
                order = Order.valueOf(parts[1]);
            }
            columnOrderings.add(new ColumnOrdering(column, order));
        }
        return new OrderingInfo(columnOrderings);
    }

    private void handleValidationError(HttpServletResponse response, ValidatorException e) throws IOException {
        for (String error : e.getErrors()) {
            response.getWriter().println(error);
        }
        response.getWriter().close();
    }

}
