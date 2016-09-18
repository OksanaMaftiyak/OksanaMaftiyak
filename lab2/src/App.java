
public class App {
    public static void main(String... args) {
        Controller controller = new Controller();
        Library library = controller.createLibrary();
        controller.addBookToLibrary(library);
        controller.addEmployeeToLibrary(library);
        controller.returnBook(library);
        controller.takeBook(library);
        controller.selectYearPublication(library);
        controller.selectAuthor(library);
        controller.selectBookTitles(library);
        library.sortBookOfAuthor();
        library.sortBookOfYear();
        library.sortBookTitle();



    }
}
