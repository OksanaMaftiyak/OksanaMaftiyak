import java.util.*;

public class Library {

    private String name;
    private double startHour;
    private double endHour;

    private final List<Book> books;
    private final List<Ticket> tickets;
    private final List<Employee> employees;
    private final List<User> users;

    public Library(String name, double startHour, double endHour, List<Book> books, List<Ticket> tickets, List<Employee> employees, List<User> users) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.tickets = new ArrayList<>(tickets);
        this.employees = new ArrayList<>(employees);
        this.books = new ArrayList<>(books);
        this.users = new ArrayList<>(users);
        this.name = name;
    }

    public ArrayList<Book> selectBooksByYear(int yearPublication) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYearPublication() == yearPublication) {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> selectBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (Objects.equals(book.getAuthor(), author)) {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> selectBooksByTitle(String bookTitle) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (Objects.equals(book.getBookTitle(), bookTitle)) {
                result.add(book);
            }
        }
        return result;
    }

    public void returnBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book couldn't be null");
        }
        returnBook(book.getId());
    }

    public void returnBook(int bookId) {
        Book book = getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        Ticket ticket = book.getTicket();
        ticket.setDateReturning(new Date());
        book.setTicket(null);
    }

    public User getUserById(final int userId) {
        for (User user : users) {
            if (userId == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public Book getBookById(final int bookId) {
        for (Book book : books) {
            if (bookId == book.getId()) {
                return book;
            }
        }
        return null;
    }

    public void takeBook(int bookId, int userId, int pay) {
        Book book = getBookById(bookId);
        User user = getUserById(userId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Ticket ticket = new Ticket(book, new Date(), null, pay, user);
        addTicket(ticket);
        book.setTicket(ticket);
    }

    public void printAllBooksSorted(Comparator<Book> comparator) {
        List<Book> allBooks = new ArrayList<>(books);
        Collections.sort(allBooks, comparator);
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }

    public List<User> getUsers() {
        return users;
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

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public double getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

}
