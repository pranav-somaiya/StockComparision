package com.example.opl_grp9_proj;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;  // Display stock symbol or welcome message

    private Stage stage;  // Store the current Stage (window)

    // This method sets the welcome message (username or stock symbol)
    public void setWelcomeMessage(String message) {
        if (welcomeLabel != null) {
            welcomeLabel.setText(message);  // Set text in label to show stock symbol or welcome message
        } else {
            System.err.println("Error: welcomeLabel is null");
        }
    }

    // Called when the user clicks a button to show the stock chart
    @FXML
    private void onShowChartClick() {
        String stockSymbol = welcomeLabel.getText();  // Assuming the symbol is in the label text
        try {
            if (stockSymbol != null && !stockSymbol.trim().isEmpty()) {
                StockChartApp.start(stage, stockSymbol);
            } else {
                System.err.println("Error: Stock symbol is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setter to inject Stage from the LoginApp or any other part
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
