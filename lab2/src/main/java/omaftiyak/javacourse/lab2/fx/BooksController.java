package omaftiyak.javacourse.lab2.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import omaftiyak.javacourse.lab2.model.Book;
import omaftiyak.javacourse.lab2.service.BookService;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField year;

    @FXML
    private TextField genre;

    @FXML
    private TextField description;

    @FXML
    private TextField language;

    @FXML
    private TableView<Book> tableView;

    private BookService bookService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (TableColumn<Book, ?> column : tableView.getColumns()) {
            column.setCellValueFactory(new PropertyValueFactory<>(column.getId()));
        }
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                onSelect(newValue);
            }
        });
    }

    private void onSelect(Book book) {
        if (book != null) {
            id.setText(String.valueOf(book.getId()));
            year.setText(String.valueOf(book.getYearPublication()));
            author.setText(book.getAuthor());
            description.setText(book.getDescription());
            genre.setText(book.getGenre());
            language.setText(book.getLanguage());
            title.setText(book.getBookTitle());
        }
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
        onRefreshInternal();
    }

    @FXML
    private void onFindByIdInternal() {
        tableView.getItems().clear();
        Book book = bookService.findBookById(Integer.parseInt(id.getText()));
        if (book != null) {
            tableView.getItems().addAll(book);
        }
    }

    @FXML
    private void onFindByAuthorInternal() {
        tableView.getItems().clear();
        tableView.getItems().addAll(bookService.findBooksByAuthor(author.getText()));
    }

    @FXML
    private void onFindByYearInternal() {
        tableView.getItems().clear();
        tableView.getItems().addAll(bookService.findBooksByYear(Integer.parseInt(year.getText())));
    }

    @FXML
    public void onFindByTitleInternal() {
        tableView.getItems().clear();
        tableView.getItems().addAll(bookService.findBookByTitle(title.getText()));
    }

    @FXML
    private void onSaveInternal() {
        Book book = tableView.getSelectionModel().getSelectedItem();
        if (book != null) {
            long id = book.getId();
            fillBook(book);
            book.setId(id);
            bookService.update(book);
            tableView.refresh();
        }
    }

    @FXML
    private void onSaveAsNewInternal() {
        Book book = new Book();
        fillBook(book);
        bookService.addBook(book);
        tableView.getItems().add(book);
        tableView.getSelectionModel().select(book);
    }

    @FXML
    private void onDeleteInternal() {
        Book book = tableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected book?" + System.lineSeparator() + book.toString(), ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            bookService.delete(book.getId());
            tableView.getItems().remove(book);
        }
    }

    @FXML
    public void onRefreshInternal() {
        tableView.getItems().clear();
        tableView.getItems().addAll(bookService.selectAllBooks());
    }

    private void fillBook(Book book) {
        // todo validate fields before set them to book
        book.setId(Integer.parseInt(id.getText()));
        book.setYearPublication(Integer.parseInt(year.getText()));
        book.setAuthor(author.getText());
        book.setBookTitle(title.getText());
        book.setDescription(description.getText());
        book.setLanguage(language.getText());
        book.setGenre(genre.getText());
    }
}
