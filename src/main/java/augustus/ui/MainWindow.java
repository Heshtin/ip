package augustus.ui;

import augustus.Augustus;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main Augustus GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Augustus augustus;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/PublicUser.png"));

    private final Image augustusImage =
            new Image(this.getClass().getResourceAsStream("/images/AugustusPic.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Augustus chatbot instance into the GUI.
     *
     * @param augustus Augustus chatbot instance
     */
    public void setAugustus(Augustus augustus) {
        this.augustus = augustus;
    }

    /**
     * Processes user input and displays the user's message and Augustus's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isBlank()) {
            return;
        }

        String response = augustus.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAugustusDialog(response, augustusImage)
        );

        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
