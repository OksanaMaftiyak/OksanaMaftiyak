package model;

import common.IdGenerator;

import java.util.Calendar;
import java.util.Objects;


public class Ticket {

    private int id;
    private int userId;
    private int bookId;
    private Calendar dateTaking;
    private Calendar dateReturning;
    private int pay;

    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    public Ticket(int bookId, int userId, Calendar dateTaking, Calendar dateReturning, int pay) {
        this.id = ID_GENERATOR.nextId();
        this.bookId = bookId;
        this.userId = userId;
        this.dateTaking = dateTaking;
        this.dateReturning = dateReturning;
        this.pay = pay;
    }

    public Ticket(String str) {
        String[] parts = str.split("\\s");
        int fieldIndex = 0;
        this.id = Integer.parseInt(parts[fieldIndex++]);
        this.bookId = Integer.parseInt(parts[fieldIndex++]);
        this.userId = Integer.parseInt(parts[fieldIndex++]);
        this.dateTaking = Calendar.getInstance();
        this.dateTaking.setTimeInMillis(Long.parseLong(parts[fieldIndex++]));
        this.dateReturning = Calendar.getInstance();
        this.dateReturning.setTimeInMillis(Long.parseLong(parts[fieldIndex++]));
        this.pay = Integer.parseInt(parts[fieldIndex++]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Calendar getDateTaking() {
        return dateTaking;
    }

    public void setDateTaking(Calendar dateTaking) {
        this.dateTaking = dateTaking;
    }

    public Calendar getDateReturning() {
        return dateReturning;
    }

    public void setDateReturning(Calendar dateReturning) {
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
