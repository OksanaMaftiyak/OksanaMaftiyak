package omaftiyak.javacourse.lab2.dao;

import omaftiyak.javacourse.lab2.model.BookLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLibraryDao {

    public List<BookLibrary> selectAll() {
        List<BookLibrary> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, name FROM libraries");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(readBookLibrary(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void persist(BookLibrary bookLibrary) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO libraries (name) VALUES (?)");
            ps.setString(1, bookLibrary.getName());
            ps.execute();
            bookLibrary.setId(ConnectionFactory.getLastId(con));
        } catch (SQLException e) {
            throw new RuntimeException("Could not persist book library" + bookLibrary, e);
        }

    }

    private BookLibrary readBookLibrary(ResultSet rs) throws SQLException {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.setId(rs.getLong("id"));
        bookLibrary.setName(rs.getString("name"));
        return bookLibrary;
    }

}
