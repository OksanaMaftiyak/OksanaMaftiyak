import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);

    public Library createLibrary() {
        String name = promptString("Enter name of Library:");
        System.out.println("Enter start and end hours:");
        double start = scanner.nextDouble(), end = scanner.nextDouble();
        if (start > 24 || start < 0 || end < 0 || end > 24) {
            System.out.println("Invalid work hours!!!");
            return null;
        }
        return new Library(name, start, end, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void addBookToLibrary(Library library) {
        Book book;

        String title = promptString("Enter title of book:");
        String name = promptString("Enter author`s name:");
        int year = promptInt("Enter year publication:");
        String language = promptString("Enter language of book:");
        String description = promptString("Enter description of book:");
        String genre = promptString("Enter genre of book:");
        book = new Book(title, name, year, language, description, genre, this.hashCode(), null);
        library.addBook(book);
    }

    public void returnBook(Library library) {

        int title = promptInt("Enter id book:");
        library.returnBook(title);
    }
    public void takeBook(Library library) {

        int id = promptInt("Enter id book:");
        int idUser = promptInt("Enter id user:");
        int pay = promptInt("Enter pay:");
        library.takeBook(id,pay,idUser);
    }

    public void addEmployeeToLibrary(Library library) {
        Employee employee;

        String firstName = promptString("Enter first name of employee:");
        String lastName = promptString("Enter last name of employee");
        int year = promptInt("Enter year to birth:");
        String position = promptString("Enter position of employee:");
        int salary = promptInt("Enter salary of employee:");
        employee = new Employee(this.hashCode(), firstName, lastName, position, year, salary);
        library.addEmployee(employee);
    }


    public ArrayList<Book> selectYearPublication(Library library) {
        int year = promptInt("Enter year publication: ");
        return library.selectBookYear(year);
    }

    public ArrayList<Book> selectAuthor(Library library) {
        String author = promptString("Enter author: ");
        return library.selectBookAuthor(author);
    }

    public ArrayList<Book> selectBookTitles(Library library) {
        String title = promptString("Enter book titles: ");
        return library.selectBookTitle(title);
    }

    private int promptInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    private String promptString(String message) {
        System.out.println(message);
        return scanner.next();
    }

}
