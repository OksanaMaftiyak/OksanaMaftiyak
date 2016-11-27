package omaftiyak.javacourse.lab2.service;

import omaftiyak.javacourse.lab2.dao.BookLibraryDao;
import omaftiyak.javacourse.lab2.model.BookLibrary;

import java.util.List;

public class BookLibraryService {

    private BookLibraryDao dao = new BookLibraryDao();

    public List<BookLibrary> listAllLibraries() {
        return dao.selectAll();
    }

    public void create(BookLibrary bookLibrary) {
        dao.persist(bookLibrary);
    }

}
