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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private static final long DEFAULT_LIBRARY_ID = 1L;
    private BookService bookService = new BookService();
    private EmployeeService employeeService = new EmployeeService();
    private TicketService ticketService = new TicketService();
    private AbonentService abonentService = new AbonentService();

    private BookValidator bookValidator = new BookValidator();
    private EmployeeValidator employeeValidator = new EmployeeValidator();
    private AbonentValidator abonentValidator = new AbonentValidator();


    Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    private String[] promptBook() {
        String title = promptString("Enter title of book:");
        String name = promptString("Enter author`s name:");
        String year = promptString("Enter year publication:");
        String language = promptString("Enter language of book:");
        String description = promptString("Enter description of book:");
        String genre = promptString("Enter genre of book:");
        return new String[]{year, name, title, description, language, genre};
    }

    private void takeBook() {
        int bookId = promptInt("Enter book id:");
        int userId = promptInt("Enter user id:");
        int pay = promptInt("Enter pay:");
        ticketService.takeBook(bookId, userId, pay);
    }

    private String[] promptEmployee() {
        String firstName = promptString("Enter first name of employee:");
        String lastName = promptString("Enter last name of employee");
        String year = promptString("Enter year of birth:");
        String position = promptString("Enter position of employee:");
        String salary = promptString("Enter salary of employee:");
        return new String[]{firstName, lastName, position, year, salary};
    }

    private String[] promptAbonent() {
        String firstName = promptString("Enter first name of abonent:");
        String lastName = promptString("Enter last name of abonent");
        String yearOfBirth = promptString("Enter year of birth:");
        return new String[]{firstName,lastName,yearOfBirth};
    }

    private void printBooksByYear(Library library) {
        int year = promptInt("Enter year of publication:");
        for (Book book : bookService.findBooksByYear(DEFAULT_LIBRARY_ID, year)) {
            System.out.println(book);
        }
    }

    private void printBooksByAuthor(Library library) {
        String authorName = promptString("Enter author: ");
        for (Book book : bookService.findBooksByAuthor(DEFAULT_LIBRARY_ID, authorName)) {
            System.out.println(book);
        }
    }

    private void printBooksByTitle(Library library) {
        String title = promptString("Enter book titles: ");
        for (Book book : bookService.selectBooksByTitle(DEFAULT_LIBRARY_ID, title)) {
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
        List<Book> allBooks = bookService.getAllBooksForLibrary(DEFAULT_LIBRARY_ID);
        Collections.sort(allBooks, comparator);
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }


    public void run() {
        Library library = Library.getLibrary();

        for (int i = 0; i < 3; i++) {
            String[] book = promptBook();
            try {
                bookValidator.validate(book);
                bookService.addBook(DEFAULT_LIBRARY_ID, new Book(book));
                break;
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            String[] employee = promptEmployee();
            try {
                employeeValidator.validate(employee);
                employeeService.addEmployee(new Employee(employee));
                break;
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            String[] abonent = promptAbonent();
            try {
                abonentValidator.validate(abonent);
                abonentService.addAbonent(new Abonent(abonent));
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
