package omaftiyak.javacourse.lab2.dao;

import omaftiyak.javacourse.lab2.model.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Provides access to tickets storage
 */
public class TicketDao implements Dao<Ticket> {

    @Override
    public void persist(Ticket ticket) {
        try (Connection connection = ConnectionFactory.getCon()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "insert into tickets (book_id, abonent_id, pay, date_taking, date_returning) values (?, ?, ?, ?, null)",
                    Statement.RETURN_GENERATED_KEYS
            )) {
                ps.setLong(1, ticket.getBookId());
                ps.setInt(2, ticket.getUserId());
                ps.setInt(3, ticket.getPay());
                ps.setTimestamp(4, Timestamp.valueOf(ticket.getDateTaking()));
                ps.execute();
                ticket.setId(ConnectionFactory.getLastId(connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not persist ticket", e);
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Connection connection = ConnectionFactory.getCon()) {
            try (PreparedStatement ps = connection.prepareStatement("update tickets set date_returning = ? , pay = ? where id = ?")) {
                ps.setTimestamp(1, Timestamp.valueOf(ticket.getDateReturning()));
                ps.setInt(2, ticket.getPay());
                ps.setLong(3, ticket.getId());
                ps.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not update ticket", e);
        }
    }

    public Ticket findOpenTicketByBookId(int bookId) {
        try (Connection connection = ConnectionFactory.getCon()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "select id, book_id, abonent_id, pay, date_taking, date_returning from tickets where book_id = ? and date_returning is null"
            )) {
                ps.setInt(1, bookId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(rs.getInt("id"));
                        ticket.setBookId(rs.getInt("book_id"));
                        ticket.setUserId(rs.getInt("abonent_id"));
                        ticket.setPay(rs.getInt("pay"));
                        ticket.setDateTaking(LocalDateTime.ofInstant(rs.getTimestamp("date_taking").toInstant(), ZoneId.systemDefault()));
                        if (rs.getTimestamp("date_returning") != null) {
                            ticket.setDateReturning(LocalDateTime.ofInstant(rs.getTimestamp("date_returning").toInstant(), ZoneId.systemDefault()));
                        }
                        return ticket;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find ticket", e);
        }
    }

    @Override
    public Ticket findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Ticket> selectAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(long id) {
        throw new UnsupportedOperationException();
    }

}
