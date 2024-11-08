package com.example.opl_grp9_proj;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloViewController {
    @FXML private TextField usernameField;  // Username input field
    @FXML private PasswordField passwordField;  // Password input field
    @FXML private Label errorLabel;  // Label for showing error messages

    private LoginApp loginApp;  // Reference to the LoginApp instance for scene transitions

    // Set the LoginApp instance for this controller
    public void setLoginApp(LoginApp loginApp) {
        this.loginApp = loginApp;
    }

    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in both fields.");  // Show error if fields are empty
        } else {
            // Clear any previous error messages
            errorLabel.setText("");

            // Proceed to the welcome view with the username
            loginApp.setUsername(username);
            loginApp.showWelcomeView(username);  // Pass the username to the welcome view
        }
    }
}
