package omaftiyak.javacourse.lab2.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import omaftiyak.javacourse.lab2.service.BookService;

/**
 * Loads UI elements from file and initialize screen components
 */
public class BooksScreen {

    private Pane pane;
    private BooksController controller;

    /**
     * Creates new screen
     */
    public BooksScreen() {
        loadComponents();
        initComponents();
    }

    /**
     * Loads pane and controller from a FXML file
     */
    private void loadComponents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BooksView.fxml"));
            pane = loader.load();
            controller = loader.getController();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Could not load view", e);
        }
    }

    /**
     * Initialize loaded components
     */
    private void initComponents() {
        controller.setBookService(new BookService());
    }

    /**
     * Gets pane to be displayed on main window
     *
     * @return pane containing UI controls
     */
    public Pane getPane() {
        return pane;
    }

}
