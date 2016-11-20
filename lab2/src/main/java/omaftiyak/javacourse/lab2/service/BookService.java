package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.dao.BookDao;
import omaftiyak.javacourse.lab2.model.Book;

import java.util.List;

/**
 * Service to manage books
 */
public class BookService {

    private BookDao dao = new BookDao();

    /**
     * Adds new book to library
     *
     * @param book book to add
     */
    public void addBook(Book book) {
        dao.persist(book);
    }

    /**
     * Finds book y its id
     *
     * @param id book id
     * @return book or null if not found
     */
    public Book findBookById(final int id) {
        return dao.findById(id);
    }

    /**
     * Finds all books published in provided year
     *
     * @param year year of publication
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByYear(int year) {
        return dao.findBooksByYear(year);
    }

    /**
     * Finds all books written by provided author
     *
     * @param author author
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    /**
     * Finds all books with title as provided
     *
     * @param title book title
     * @return list with books or empty list if not found
     */
    public List<Book> selectBooksByTitle(String title) {
        return dao.selectBooksByTitle(title);
    }

    /**
     * Selects all books
     *
     * @return list with all books
     */
    public List<Book> selectAllBooks() {
        return dao.selectAll();
    }

    /**
     * Checks if book is not taken by any abonent
     *
     * @param book book to be checked
     * @return true if book could be taken from library, otherwise - false
     */
    public boolean isBookAvailable(Book book) {
        return dao.isBookAvailable(book.getId());
    }

    /**
     * Updates existing book
     *
     * @param book book
     */
    public void update(Book book) {
        dao.update(book);
    }

    /**
     * Deletes book by id
     *
     * @param id book id
     */
    public void delete(long id) {
        dao.deleteById(id);
    }

    /**
     * Finds books matching title
     *
     * @param title title
     * @return list of books
     */
    public List<Book> findBookByTitle(String title) {
        return dao.selectBooksByTitle(title);
    }
}
