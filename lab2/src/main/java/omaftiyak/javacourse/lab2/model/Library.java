package omaftiyak.javacourse.lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

    private static Library library;

    private String name;
    private double startHour;
    private double endHour;

    private final List<Book> books = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Abonent> abonents = new ArrayList<>();

    public static Library getLibrary() {
        if (library == null) {
            library = new Library();
        }
        return library;
    }

    private Library() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartHour() {
        return startHour;
    }

    public void setStartHour(double startHour) {
        this.startHour = startHour;
    }

    public double getEndHour() {
        return endHour;
    }

    public void setEndHour(double endHour) {
        this.endHour = endHour;
    }

    public List<Book> getBooks() {
        return books;
    }

    /**
     * Deprecated - please use TicketDao
     */
    @Deprecated
    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Abonent> getAbonents() {
        return abonents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Library)) {
            return false;
        }
        Library library = (Library) obj;
        return Objects.equals(name, library.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("name: '").append(name);
        sb.append(", startHour: ").append(startHour);
        sb.append(", endHour: ").append(endHour);
        sb.append(", books: ").append(books.size());
        sb.append(", tickets: ").append(tickets.size());
        sb.append(", employees: ").append(employees.size());
        sb.append(", abonents: ").append(abonents.size());
        sb.append("]");
        return sb.toString();
    }

}
