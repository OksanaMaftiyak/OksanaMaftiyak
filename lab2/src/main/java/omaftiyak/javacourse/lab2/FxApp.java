package omaftiyak.javacourse.lab2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import omaftiyak.javacourse.lab2.fx.BooksScreen;

/**
 * Launcher of App in JavaFX mode
 */
public class FxApp extends Application {

    private static final String APP_TITLE = "Library";

    private static final int DEFAULT_WINDOW_WIDTH = 800;
    private static final int DEFAULT_WINDOW_HEIGHT = 600;

    /**
     * Entry point
     *
     * @param args args - all args are ignored now
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws Exception {
        BooksScreen screen = new BooksScreen();
        stage.setScene(new Scene(screen.getPane(), DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
        stage.setTitle(APP_TITLE);
        stage.show();
    }

}
