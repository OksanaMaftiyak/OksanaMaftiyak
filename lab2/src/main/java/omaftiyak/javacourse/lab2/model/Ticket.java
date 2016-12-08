package omaftiyak.javacourse.lab2.model;

import omaftiyak.javacourse.lab2.common.IdGenerator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Objects;


public class Ticket {

    private long id;
    private long userId;
    private long bookId;
    private LocalDateTime dateTaking;
    private LocalDateTime dateReturning;
    private int pay;

    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    public Ticket(long id, long bookId, long userId, LocalDateTime dateTaking, LocalDateTime dateReturning, int pay) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dateTaking = dateTaking;
        this.dateReturning = dateReturning;
        this.pay = pay;
    }

    public Ticket(long bookId, long userId, LocalDateTime dateTaking, LocalDateTime dateReturning, int pay) {
        this(ID_GENERATOR.nextId(), bookId, userId, dateTaking, dateReturning, pay);
    }

    public Ticket(String str) {
        String[] parts = str.split("\\s");
        int fieldIndex = 0;
        this.id = Integer.parseInt(parts[fieldIndex++]);
        this.bookId = Integer.parseInt(parts[fieldIndex++]);
        this.userId = Integer.parseInt(parts[fieldIndex++]);
        this.dateTaking = LocalDateTime.ofEpochSecond(Long.parseLong(parts[fieldIndex++]), 0, ZoneOffset.UTC);
        this.dateReturning = LocalDateTime.ofEpochSecond(Long.parseLong(parts[fieldIndex++]), 0, ZoneOffset.UTC);
        this.pay = Integer.parseInt(parts[fieldIndex++]);
    }

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getDateTaking() {
        return dateTaking;
    }

    public void setDateTaking(LocalDateTime dateTaking) {
        this.dateTaking = dateTaking;
    }

    public LocalDateTime getDateReturning() {
        return dateReturning;
    }

    public void setDateReturning(LocalDateTime dateReturning) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("id: ").append(id);
        sb.append(", userId: ").append(userId);
        sb.append(", bookId: ").append(bookId);
        sb.append(", dateTaking: ").append(dateTaking);
        sb.append(", dateReturning: ").append(dateReturning);
        sb.append(", pay: ").append(pay);
        sb.append("]");
        return sb.toString();
    }

}
