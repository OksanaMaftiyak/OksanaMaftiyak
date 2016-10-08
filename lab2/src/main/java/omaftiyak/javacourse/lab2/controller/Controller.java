package omaftiyak.javacourse.lab2.controller;

import omaftiyak.javacourse.lab2.model.Abonent;
import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.model.Employee;
import omaftiyak.javacourse.lab2.model.Library;
import omaftiyak.javacourse.lab2.service.AbonentService;
import omaftiyak.javacourse.lab2.service.BookService;
import omaftiyak.javacourse.lab2.service.EmployeeService;
import omaftiyak.javacourse.lab2.service.TicketService;
import omaftiyak.javacourse.lab2.validator.AbonentValidator;
import omaftiyak.javacourse.lab2.validator.BookValidator;
import omaftiyak.javacourse.lab2.validator.EmployeeValidator;
import omaftiyak.javacourse.lab2.validator.ValidatorException;

import java.util.*;

public class Controller {

    private BookService bookService = new BookService();
    private EmployeeService employeeService = new EmployeeService();
    private TicketService ticketService = new TicketService();
    private AbonentService abonentService = new AbonentService();

    private BookValidator bookValidator = new BookValidator();
    private EmployeeValidator employeeValidator = new EmployeeValidator();
    private AbonentValidator abonentValidator = new AbonentValidator();


    Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    private Book promptBook() {
        String title = promptString("Enter title of book:");
        String name = promptString("Enter author`s name:");
        int year = promptInt("Enter year publication:");
        String language = promptString("Enter language of book:");
        String description = promptString("Enter description of book:");
        String genre = promptString("Enter genre of book:");
        return new Book(year, name, title, description, language, genre);
    }

    private void takeBook() {
        int bookId = promptInt("Enter book id:");
        int userId = promptInt("Enter user id:");
        int pay = promptInt("Enter pay:");
        ticketService.takeBook(bookId, userId, pay);
    }

    private Employee promptEmployee() {
        String firstName = promptString("Enter first name of employee:");
        String lastName = promptString("Enter last name of employee");
        int year = promptInt("Enter year of birth:");
        String position = promptString("Enter position of employee:");
        int salary = promptInt("Enter salary of employee:");
        return new Employee(firstName, lastName, position, year, salary);
    }

    private Abonent promptAbonent() {
        String firstName = promptString("Enter first name of abonent:");
        String lastName = promptString("Enter last name of abonent");
        int yearOfBirth = promptInt("Enter year of birth:");
        return new Abonent(firstName, lastName, yearOfBirth);
    }

    private void printBooksByYear(Library library) {
        int year = promptInt("Enter year of publication:");
        for (Book book : bookService.findBooksByYear(year)) {
            System.out.println(book);
        }
    }

    private void printBooksByAuthor(Library library) {
        String authorName = promptString("Enter author: ");
        for (Book book : bookService.findBooksByAuthor(authorName)) {
            System.out.println(book);
        }
    }

    private void printBooksByTitle(Library library) {
        String title = promptString("Enter book titles: ");
        for (Book book : bookService.selectBooksByTitle(title)) {
            System.out.println(book);
        }
    }

    private int promptInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input '%s' for integer. Please try again...");
            }
        }
    }

    private String promptString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    private void printAllBooksSorted(Comparator<Book> comparator) {
        List<Book> allBooks = bookService.selectAllBooks();
        Collections.sort(allBooks, comparator);
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }


    public void run() {
        Library library = Library.getLibrary();

        for (int i = 0; i < 3; i++) {
            Book book = promptBook();
            try {
                bookValidator.validate(book);
                bookService.addBook(book);
                break;
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            Employee employee = promptEmployee();
            try {
                employeeValidator.validate(employee);
                employeeService.addEmployee(employee);
                break;
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            Abonent abonent = promptAbonent();
            try {
                abonentValidator.validate(abonent);
                abonentService.addAbonent(abonent);
                break;
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        takeBook();
        ticketService.returnBook(promptInt("Enter id book:"));

        printBooksByYear(library);
        printBooksByAuthor(library);
        printBooksByTitle(library);

        printAllBooksSorted(Book.AuthorComparator);
        printAllBooksSorted(Book.YearComparator);
        printAllBooksSorted(Book.TitleComparator);
    }

}
