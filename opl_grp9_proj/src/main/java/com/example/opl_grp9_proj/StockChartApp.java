package com.example.opl_grp9_proj;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StockChartApp {

    private static final double WINDOW_WIDTH = 800;  // Window width
    private static final double WINDOW_HEIGHT = 600; // Window height
    private static final int DATA_WINDOW_SIZE = 10;  // Number of intervals visible at once

    private static int currentStartIndex = 0;
    private static List<StockData> stockDataList;  // List to store fetched stock data

    // Method to start the chart application
    public static void start(Stage stage, String stockSymbol) throws IOException {
        // Fetch stock data from Alpha Vantage using the entered symbol
        stockDataList = AlphaVantageAPI.fetchStockData(stockSymbol);

        // Create the X and Y axes for the chart
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price (USD)");

        // Create a LineChart using the X and Y axes
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(stockSymbol + " Stock Prices Over Time");

        // Create a series to hold the stock data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(stockSymbol + " Stock Price");

        // Add stock data to the series for the current window (starting index)
        updateChartData(series, currentStartIndex);

        // Add the series to the chart
        lineChart.getData().add(series);

        // Create the root pane and add the chart to it
        BorderPane root = new BorderPane();
        root.setCenter(lineChart);

        // Create an HBox to hold the buttons for left and right scrolling
        HBox controls = new HBox(10);
        Button leftButton = new Button("<-");
        Button rightButton = new Button("->");

        controls.getChildren().addAll(leftButton, rightButton);

        // Add controls to the bottom of the BorderPane
        root.setBottom(controls);

        // Set up button actions for scrolling
        leftButton.setOnAction(e -> {
            if (currentStartIndex > 0) {
                currentStartIndex -= DATA_WINDOW_SIZE;
                updateChartData(series, currentStartIndex);
            }
        });

        rightButton.setOnAction(e -> {
            if (currentStartIndex + DATA_WINDOW_SIZE < stockDataList.size()) {
                currentStartIndex += DATA_WINDOW_SIZE;
                updateChartData(series, currentStartIndex);
            }
        });

        // Set up the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle(stockSymbol + " Stock Data Visualization");
        stage.show();
    }

    // Method to update the chart data for the visible window (currentStartIndex)
    private static void updateChartData(XYChart.Series<Number, Number> series, int startIndex) {
        series.getData().clear();  // Clear previous data

        int endIndex = Math.min(startIndex + DATA_WINDOW_SIZE, stockDataList.size());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm a"); // Format for showing time in 12-hour format with AM/PM

        for (int i = startIndex; i < endIndex; i++) {
            StockData stockData = stockDataList.get(i);
            try {
                long timestamp = stockData.getDateAsTimestamp();  // Get the timestamp from stockData
                String formattedTime = sdf.format(timestamp); // Format the timestamp to time (not used directly in the chart)

                // For debugging: print the formatted time (or you could use it if you want to show time on the X-axis)
                System.out.println("Time: " + formattedTime + " | Price: " + stockData.getClosePrice());

                double closePrice = stockData.getClosePrice(); // Close price for the Y-axis

                // Add the data point with formatted time as label and close price as the Y value
                series.getData().add(new XYChart.Data<>(i, closePrice)); // Use index (i) as the X value for simplicity
            } catch (Exception e) {
                e.printStackTrace();  // Handle the exception (though it's less likely to occur here)
            }
        }
    }
}
