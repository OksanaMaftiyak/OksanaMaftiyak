import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public Library createLibrary() {
        String name = promptString("Enter name of Library:");
        System.out.println("Enter start and end hours:");
        double start = scanner.nextDouble();
        double end = scanner.nextDouble();
        if (start > 24 || start < 0 || end < 0 || end > 24) {
            System.out.println("Invalid work hours!!!");
            return null;
        }
        return new Library(name, start, end, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Book promptBook() {
        String title = promptString("Enter title of book:");
        String name = promptString("Enter author`s name:");
        int year = promptInt("Enter year publication:");
        String language = promptString("Enter language of book:");
        String description = promptString("Enter description of book:");
        String genre = promptString("Enter genre of book:");
        return new Book(year, name, title, description, language, genre, null);
    }

    public void takeBook(Library library) {
        int bookId = promptInt("Enter book id:");
        int userId = promptInt("Enter user id:");
        int pay = promptInt("Enter pay:");
        library.takeBook(bookId, userId, pay);
    }

    public Employee promptEmployee() {
        String firstName = promptString("Enter first name of employee:");
        String lastName = promptString("Enter last name of employee");
        int year = promptInt("Enter year to birth:");
        String position = promptString("Enter position of employee:");
        int salary = promptInt("Enter salary of employee:");
        return new Employee(firstName, lastName, position, year, salary);
    }

    public void printBooksByYear(Library library) {
        int year = promptInt("Enter year of publication:");
        for (Book book : library.selectBooksByYear(year)) {
            System.out.println(book);
        }
    }

    public void printBooksByAuthor(Library library) {
        String author = promptString("Enter author: ");
        for (Book book : library.selectBooksByAuthor(author)) {
            System.out.println(book);
        }
    }

    public void printBooksByTitle(Library library) {
        String title = promptString("Enter book titles: ");
        for (Book book : library.selectBooksByTitle(title)) {
            System.out.println(book);
        }
    }

    private int promptInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    private String promptString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public void run() {
        Library library = createLibrary();

        library.addBook(promptBook());
        library.addEmployee(promptEmployee());
        takeBook(library);
        library.returnBook(promptInt("Enter id book:"));

        printBooksByYear(library);
        printBooksByAuthor(library);
        printBooksByTitle(library);

        library.printAllBooksSorted(Book.AuthorComparator);
        library.printAllBooksSorted(Book.YearComparator);
        library.printAllBooksSorted(Book.TitleComparator);
    }

}
