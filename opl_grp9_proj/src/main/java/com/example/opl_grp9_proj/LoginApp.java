package com.example.opl_grp9_proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class LoginApp extends Application {
    private String username;  // This stores the username after login

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the initial login view (HelloView.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-View.fxml"));
        Region root = loader.load();

        // Get the controller and pass the LoginApp instance to it
        HelloViewController helloController = loader.getController();
        helloController.setLoginApp(this);

        // Show the login view
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public void showWelcomeView(String message) {
        // Show the welcome view with the username
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome_view.fxml"));
        Region root;
        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        WelcomeController welcomeController = loader.getController();
        welcomeController.setWelcomeMessage("Welcome, " + message);  // Passing the username to the welcome view

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Welcome");
        stage.show();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
