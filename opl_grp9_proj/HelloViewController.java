package com.example.opl_grp9_proj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HelloViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorMessage;

    @FXML
    private Button okayButton;

    @FXML
    protected void handleOkayButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Please fill in both username and password!");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome_view.fxml"));
            Stage stage = (Stage) okayButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            // Pass the username to WelcomeController
            WelcomeController controller = loader.getController();
            controller.setUsername(username);
        }
    }
}
