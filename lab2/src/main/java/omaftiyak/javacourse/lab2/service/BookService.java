package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.Library;
import omaftiyak.javacourse.lab2.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service to manage books
 */
public class BookService {

    /**
     * Adds book to library
     *
     * @param book book to add
     */
    public void addBook(Book book) {
        Library.getLibrary().getBooks().add(book);
    }

    /**
     * Finds book y its id
     *
     * @param id book id
     * @return book or null if not found
     */
    public Book findBookById(final int id) {
        for (Book book : Library.getLibrary().getBooks()) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    /**
     * Finds all books published in provided year
     *
     * @param year year of publication
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByYear(int year) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : Library.getLibrary().getBooks()) {
            if (book.getYearPublication() == year) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Finds all books written by provided author
     *
     * @param author author
     * @return list with books or empty list if not found
     */
    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : Library.getLibrary().getBooks()) {
            if (Objects.equals(book.getAuthor(), author)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Finds all books with title as provided
     *
     * @param title book title
     * @return list with books or empty list if not found
     */
    public ArrayList<Book> selectBooksByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : Library.getLibrary().getBooks()) {
            if (Objects.equals(book.getBookTitle(), title)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Selects all books
     *
     * @return list with all books
     */
    public List<Book> selectAllBooks() {
        return new ArrayList<>(Library.getLibrary().getBooks());
    }

    /**
     * Checks if book is not taken by any abonent
     *
     * @param book book to be checked
     * @return true if book could be taken from library, otherwise - false
     */
    public boolean isBookAvailable(Book book) {
        // todo create BookDao and query DB if book is not assigned to any open ticket
        for (Ticket ticket : Library.getLibrary().getTickets()) {
            if (ticket.getBookId() == book.getId()) {
                return false;
            }
        }
        return true;
    }

}
