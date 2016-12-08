package omaftiyak.javacourse.lab2.controller;

import omaftiyak.javacourse.lab2.model.*;
import omaftiyak.javacourse.lab2.service.*;
import omaftiyak.javacourse.lab2.validator.*;

import java.util.Comparator;

import java.util.Scanner;

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

    private String[] promptBook() {
        String title = promptString("Enter title of book:");
        String name = promptString("Enter author`s name:");
        String year = promptString("Enter year publication:");
        String language = promptString("Enter language of book:");
        String description = promptString("Enter description of book:");
        String genre = promptString("Enter genre of book:");
        return new String[]{ title, name,year, description, language, genre};
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
        bookService.findBooksByYear(year).forEach(System.out::println);
    }

    private void printBooksByAuthor(Library library) {
        String authorName = promptString("Enter author: ");
       bookService.findBooksByAuthor(authorName).forEach(System.out::println);
    }

    private void printBooksByTitle(Library library) {
        String title = promptString("Enter book titles: ");
     bookService.selectBooksByTitle(title).forEach(System.out::println);
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
        return scanner.nextLine();
    }

    private void printAllBooksSorted(Comparator<Book> comparator) {
        bookService.selectAllBooks().stream()
                .sorted(comparator).forEach(System.out::println);
    }


    public void run() {
        Library library = Library.getLibrary();

        for (int i = 0; i < 3; i++) {
            String[] book = promptBook();
            try {
                bookValidator.validate(book);
                bookService.addBook(new Book(book));
              //  System.out.println(bookService.findBookById(promptInt("Enter id book:")).toString());
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            String[] employee = promptEmployee();
            try {
                employeeValidator.validate(employee);
                employeeService.addEmployee(new Employee(employee));
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            String[] abonent = promptAbonent();
            try {
                abonentValidator.validate(abonent);
                abonentService.addAbonent(new Abonent(abonent));
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
