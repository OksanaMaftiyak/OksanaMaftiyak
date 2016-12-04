package omaftiyak.javacourse.lab2.dao;

import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.ColumnOrdering;
import omaftiyak.javacourse.lab2.model.OrderingInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void persist(Long libraryId, Book book) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books(booktitle, author, yearpublication, genre, description, language, libraryId) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, book.getBookTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getLanguage());
            ps.setLong(7, libraryId);
            ps.execute();
            book.setId(ConnectionFactory.getLastId(con));
        } catch (SQLException e) {
            throw new RuntimeException("Could not persist book " + book, e);
        }
    }

    public void update(long libraryId, Book book) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET booktitle = ?, author = ?, yearpublication = ?, genre = ?, description = ?, language = ? WHERE id=? and libraryId=?");
            ps.setString(1, book.getBookTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getLanguage());
            ps.setLong(7, book.getId());
            ps.setLong(8, libraryId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Could not update book " + book, e);
        }
    }

    public Book findById(long libraryId, long bookId) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE libraryId = ? and  id = ?");
            ps.setLong(1, libraryId);
            ps.setLong(2, bookId);
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

    public List<Book> selectAllForLibrary(Long libraryId, OrderingInfo ordering) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books where libraryId = ?" + buildOrderingSql(ordering));
            ps.setLong(1, libraryId);
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

    public void deleteById(long libraryId, long id) {
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE libraryId =? and id = ?");
            ps.setLong(1, libraryId);
            ps.setLong(2, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findBooksByYear(Long libraryId, int year, OrderingInfo ordering) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE libraryId = ? AND yearpublication = ?" + buildOrderingSql(ordering));
            ps.setLong(1, libraryId);
            ps.setInt(2, year);
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

    public List<Book> findBooksByAuthor(Long libraryId, String author, OrderingInfo ordering) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            Statement stm = con.createStatement();
            try (ResultSet rs = stm.executeQuery("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE libraryId = " + libraryId + " AND author like '" + author + "'" + buildOrderingSql(ordering))) {
                while (rs.next()) {
                    result.add(readBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Book> selectBooksByTitle(Long libraryId, String title, OrderingInfo ordering) {
        List<Book> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getCon()) {
            PreparedStatement ps = con.prepareStatement("SELECT id, booktitle, author, yearpublication, genre, description, language FROM books WHERE libraryId = ? AND booktitle like ?" + buildOrderingSql(ordering));
            ps.setLong(1, libraryId);
            ps.setString(2, title);
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

    private String buildOrderingSql(OrderingInfo ordering) {
        if (ordering == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ColumnOrdering co : ordering.getColumnOrderings()) {
            Column column = Column.fromColumnName(co.getColumn());
            if (sb.length() == 0) {
                sb.append(" ORDER BY ");
            } else {
                sb.append(", ");
            }
            sb.append(column.getColumnName().toLowerCase());
            sb.append(" ").append(co.getOrder().toString());
        }
        return sb.toString();
    }

    private enum Column {

        TITLE("bookTitle"),
        AUTHOR("author"),
        YEAR("yearPublication");

        private String columnName;

        Column(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName() {
            return columnName;
        }

        public static Column fromColumnName(String columnName) {
            for (Column column : values()) {
                if (column.getColumnName().equals(columnName)) {
                    return column;
                }
            }
            throw new IllegalArgumentException("Unknown column name");
        }

    }

}
