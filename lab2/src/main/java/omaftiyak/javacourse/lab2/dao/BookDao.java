package omaftiyak.javacourse.lab2.dao;

import omaftiyak.javacourse.lab2.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements Dao<Book> {

    @Override
    public void persist(Book book) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books(booktitle, author, yearpublication, genre, description, language) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, book.getBookTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getLanguage());
            ps.execute();
            book.setId(ConnectionFactory.getLastId(con));
        } catch (SQLException e) {
            throw new RuntimeException("Could not persist book " + book, e);
        }
    }

    @Override
    public void update(Book book) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET booktitle = ?, author = ?, yearpublication = ?, genre = ?, description = ?, language = ? WHERE id=?");
            ps.setString(1, book.getBookTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getLanguage());
            ps.setLong(7, book.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Could not update book " + book, e);
        }
    }

    @Override
    public Book findById(long id) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE id = ?");
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return readBook(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Book> selectAll() {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(readBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void deleteById(long id) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE yearpublication = ?");
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(readBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            Statement stm = con.createStatement();
            try (ResultSet rs = stm.executeQuery("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE author like '" + author + "'")) {
                while (rs.next()) {
                    result.add(readBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Book> selectBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE booktitle like ?");
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(readBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public boolean isBookAvailable(long id) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM tickets WHERE book_id = ? and date_returning is null");
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book readBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong(1));
        book.setBookTitle(rs.getString(2));
        book.setAuthor(rs.getString(3));
        book.setYearPublication(rs.getInt(4));
        book.setGenre(rs.getString(5));
        book.setDescription(rs.getString(6));
        book.setLanguage(rs.getString(7));
        return book;
    }

}
