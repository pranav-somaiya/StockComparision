package com.example.opl_grp9_proj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class WelcomeController {

    @FXML
    private TextField stock1Field;

    @FXML
    private TextField stock2Field;

    @FXML
    private Button submitButton;

    @FXML
    private Label errorMessage;

    @FXML
    private Label welcomeMessage;  // Label to display the username

    private String username;  // Store the username

    // Set the username after login
    public void setUsername(String username) {
        this.username = username;
        welcomeMessage.setText("Welcome, " + username);
    }

    @FXML
    protected void handleSubmitButton() {
        String stock1 = stock1Field.getText().toUpperCase();
        String stock2 = stock2Field.getText().toUpperCase();

        // Validate stock symbols
        if (stock1.isEmpty() || stock2.isEmpty()) {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Please fill in both stock symbols!");
        } else {
            try {
                // Fetch stock data for the given stock symbols using the StockPriceFetcher
                StockPriceFetcher priceFetcher = new StockPriceFetcher();
                List<StockData> stock1DataList = priceFetcher.fetchStockData(stock1); // Fetch real stock data for stock1
                List<StockData> stock2DataList = priceFetcher.fetchStockData(stock2); // Fetch real stock data for stock2

                if (!stock1DataList.isEmpty() && !stock2DataList.isEmpty()) {
                    // Extract time intervals and closing prices from the StockData objects
                    String[] timeIntervals = stock1DataList.get(0).getTimeIntervals();  // Assume both stocks have the same intervals
                    double[] stock1ClosePrices = stock1DataList.get(0).getClosePrices();
                    double[] stock2ClosePrices = stock2DataList.get(0).getClosePrices();

                    // Use the StockChartApp to display the stock prices
                    StockChartApp chartApp = new StockChartApp();
                    chartApp.displayChart(timeIntervals, stock1ClosePrices, timeIntervals, stock2ClosePrices);

                } else {
                    errorMessage.setTextFill(Color.RED);
                    errorMessage.setText("Error fetching stock data!");
                }

            } catch (Exception e) {
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Error fetching stock data: " + e.getMessage());
            }
        }
    }
}
