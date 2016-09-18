import java.util.Date;
import java.util.Objects;


public class Ticket {

    private final int id;
    private final User user;
    private final Book book;
    private final Date dateTaking;
    private Date dateReturning;
    private int pay;

    public Ticket(int id, Book book, Date dateTaking, Date dateReturning, int pay, User user) {
        this.id = id;
        this.book = book;
        this.dateTaking = dateTaking;
        this.dateReturning = dateReturning;
        this.pay = pay;
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Date getDateTaking() {
        return dateTaking;
    }

    public Date getDateReturning() {
        return dateReturning;
    }

    public void setDateReturning(Date dateReturning) {
        this.dateReturning = dateReturning;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) obj;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
