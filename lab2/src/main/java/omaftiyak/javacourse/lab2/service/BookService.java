package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.dao.BookDao;
import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.OrderingInfo;

import java.util.List;

/**
 * Service to manage books
 */
public class BookService {

    private BookDao dao = new BookDao();

    /**
     * Adds new book to library
     *
     * @param libraryId library id
     * @param book      book to add
     */
    public void addBook(Long libraryId, Book book) {
        dao.persist(libraryId, book);
    }

    /**
     * Finds book y its id
     *
     * @param libraryId libraryid
     * @param bookId    book id
     * @return book or null if not found
     */
    public Book findBookById(long libraryId, long bookId) {
        return dao.findById(libraryId, bookId);
    }

    /**
     * Finds all books published in provided year
     *
     * @param libraryId library id
     * @param year      year of publication
     * @param ordering  ordering
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByYear(Long libraryId, int year, OrderingInfo ordering) {
        return dao.findBooksByYear(libraryId, year, ordering);
    }

    /**
     * Finds all books written by provided author
     *
     * @param libraryId library id
     * @param author    author
     * @param ordering  ordering
     * @return list with books or empty list if not found
     */
    public List<Book> findBooksByAuthor(Long libraryId, String author, OrderingInfo ordering) {
        return dao.findBooksByAuthor(libraryId, author, ordering);
    }

    /**
     * Finds all books with title as provided
     *
     * @param libraryId library id
     * @param title     book title
     * @param ordering  ordering
     * @return list with books or empty list if not found
     */
    public List<Book> selectBooksByTitle(Long libraryId, String title, OrderingInfo ordering) {
        return dao.selectBooksByTitle(libraryId, title, ordering);
    }

    /**
     * Selects all books for specific library
     *
     * @param libraryId library id
     * @param ordering  ordering
     * @return list with all books
     */
    public List<Book> getAllBooksForLibrary(Long libraryId, OrderingInfo ordering) {
        return dao.selectAllForLibrary(libraryId, ordering);
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
     * @param libraryId library id
     * @param book      book
     */
    public void update(long libraryId, Book book) {
        dao.update(libraryId, book);
    }

    /**
     * Deletes book by id
     *
     * @param id book id
     */
    public void delete(long libraryId, long id) {
        dao.deleteById(libraryId, id);
    }

}
