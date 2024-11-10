package com.example.opl_grp9_proj;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StockChartApp {


    public void displayChart(String[] stock1TimeIntervals, double[] stock1ClosePrices,
                             String[] stock2TimeIntervals, double[] stock2ClosePrices) {

        NumberAxis xAxis = new NumberAxis(0, 26, 1);  // X-axis from 0 to 26
        xAxis.setLabel("Time Interval");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price");


        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Price Comparison");


        XYChart.Series<Number, Number> stock1Series = new XYChart.Series<>();
        stock1Series.setName("Stock 1");
        for (int i = 0; i < stock1TimeIntervals.length; i++) {
            stock1Series.getData().add(new XYChart.Data<>(i, stock1ClosePrices[i]));
        }


        XYChart.Series<Number, Number> stock2Series = new XYChart.Series<>();
        stock2Series.setName("Stock 2");
        for (int i = 0; i < stock2TimeIntervals.length; i++) {
            stock2Series.getData().add(new XYChart.Data<>(i, stock2ClosePrices[i]));
        }


        lineChart.getData().addAll(stock1Series, stock2Series);


        Stage stage = new Stage();
        stage.setTitle("Stock Price Comparison");
        Scene scene = new Scene(lineChart, 800, 600);  // Set scene size
        stage.setScene(scene);
        stage.show();
    }
}
