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
    private Label welcomeMessage;

    private String username;


    public void setUsername(String username) {
        this.username = username;
        welcomeMessage.setText("Welcome, " + username);
    }

    @FXML
    protected void handleSubmitButton() {
        String stock1 = stock1Field.getText().toUpperCase();
        String stock2 = stock2Field.getText().toUpperCase();

        if (stock1.isEmpty() || stock2.isEmpty()) {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Please fill in both stock symbols!");
        } else {
            try {

                StockPriceFetcher priceFetcher = new StockPriceFetcher();
                List<StockData> stock1DataList = priceFetcher.fetchStockData(stock1);
                List<StockData> stock2DataList = priceFetcher.fetchStockData(stock2);

                if (!stock1DataList.isEmpty() && !stock2DataList.isEmpty()) {

                    String[] timeIntervals = stock1DataList.get(0).getTimeIntervals(); // Assume both stocks have the same time intervals
                    double[] stock1ClosePrices = stock1DataList.get(0).getClosePrices();
                    double[] stock2ClosePrices = stock2DataList.get(0).getClosePrices();

                    StockChartApp chartApp = new StockChartApp();
                    // Pass stock symbols along with time intervals and close prices
                    chartApp.displayChart(stock1, timeIntervals, stock1ClosePrices, stock2, timeIntervals, stock2ClosePrices);

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
