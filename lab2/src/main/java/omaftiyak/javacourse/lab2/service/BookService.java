package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.Library;
import omaftiyak.javacourse.lab2.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Book findBookById(final long id) {
        return Library.getLibrary().getBooks().stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds all books published in provided year
     *
     * @param year year of publication
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByYear(int year) {
     return  Library.getLibrary().getBooks().stream()
                .filter(book -> book.getYearPublication()==year)
                .collect(Collectors.toList());
    }

    /**
     * Finds all books written by provided author
     *
     * @param author author
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByAuthor(String author) {
       return Library.getLibrary().getBooks().stream()
               .filter(book -> book.getAuthor().equals(author))
               .collect(Collectors.toList());
    }

    /**
     * Finds all books with title as provided
     *
     * @param title book title
     * @return list with books or empty list if not found
     */
    public List<Book> selectBooksByTitle(String title) {
       return Library.getLibrary().getBooks().stream()
                .filter(book -> book.getBookTitle().equals(title))
                .collect(Collectors.toList());
    }

    /**
     * Selects all books
     *
     * @return list with all books
     */
    public List<Book> selectAllBooks() {
        return Library.getLibrary().getBooks().stream()
                .collect(Collectors.toList());
    }

    /**
     * Checks if book is not taken by any abonent
     *
     * @param book book to be checked
     * @return true if book could be taken from library, otherwise - false
     */


}
