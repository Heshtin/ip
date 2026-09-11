package augustus;

import java.io.IOException;

import augustus.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Augustus using FXML.
 */
public class Main extends Application {

    private final Augustus augustus =
            new Augustus("./src/data/augustus.txt");

    /**
     * Starts the JavaFX GUI.
     *
     * @param stage primary stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.setTitle("Augustus");
            stage.setResizable(false);

            fxmlLoader.<MainWindow>getController().setAugustus(augustus);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
