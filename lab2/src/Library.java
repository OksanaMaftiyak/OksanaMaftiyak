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

    public ArrayList<Book> selectBookYear(int yearPublication) {
        ArrayList<Book> booksOfYears = new ArrayList<>();
        sortBookOfAuthor();
        int i = 0;
        while (books.get(i).getYearPublication() != yearPublication) {
            i++;
        }
        while ((books.get(i).getYearPublication() == yearPublication)) {
            booksOfYears.add(books.get(i));
            i++;
        }
        return booksOfYears;
    }

    public ArrayList<Book> selectBookAuthor(String author) {
        ArrayList<Book> booksOfAuthor = new ArrayList<>();
        sortBookOfAuthor();
        int i = 0;
        while (!Objects.equals(books.get(i).getAuthor(), author)) {
            i++;
        }
        while (Objects.equals(books.get(i).getAuthor(), author)) {
            booksOfAuthor.add(books.get(i));
            i++;
        }
        return booksOfAuthor;
    }

    public ArrayList<Book> selectBookTitle(String bookTitle) {
        ArrayList<Book> booksOfTitle = new ArrayList<>();
        sortBookOfAuthor();
        int i = 0;
        while (!Objects.equals(books.get(i).getBookTitle(), bookTitle)) {
            i++;
        }
        while (Objects.equals(books.get(i).getBookTitle(), bookTitle)) {
            booksOfTitle.add(books.get(i));
            i++;
        }
        return booksOfTitle;
    }


    public void returnBook(Book book) {
        int i = tickets.indexOf(book.getTicket());
        tickets.get(i).setDateReturning(new Date());
        i = books.indexOf(book);
        books.get(i).setTicket(null);
    }
    public void returnBook(int id) {
        int i=0;
        while(id!=books.get(i).getId()|| books.get(i)!=null){
            i++;
        }
        tickets.indexOf(books.get(i).getTicket());
        tickets.get(i).setDateReturning(new Date());
        books.get(i).setTicket(null);
    }
    public void takeBook(int id, int pay, int idUser) {
        int i=0,j=0;
        while(id!=books.get(i).getId()|| books.get(i)!=null){
            i++;
        }
        while(id!=users.get(j).getId()|| users.get(j)!=null){
            j++;
        }
        Ticket ticket = new Ticket(this.hashCode(), books.get(i), new Date(), null, pay, users.get(j));
        addTicket(ticket);
        books.get(i).setTicket(ticket);
    }

    public void sortBookOfAuthor() {
        Collections.sort(books, Book.AuthorComparator);
    }

    public void sortBookOfYear() {
        Collections.sort(books, Book.YearComparator);
    }


    public void sortBookTitle() {
        Collections.sort(books, Book.BookTitleComparator);
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
