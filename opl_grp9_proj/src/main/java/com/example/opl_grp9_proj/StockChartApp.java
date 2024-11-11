package com.example.opl_grp9_proj;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class StockChartApp {

    public void displayChart(String stock1Symbol, String[] stock1TimeIntervals, double[] stock1ClosePrices,
                             String stock2Symbol, String[] stock2TimeIntervals, double[] stock2ClosePrices) {


        double stock1Change = calculatePercentageChange(stock1ClosePrices);
        double stock2Change = calculatePercentageChange(stock2ClosePrices);


        double stock1FirstClose = stock1ClosePrices[0];
        double stock1FinalClose = stock1ClosePrices[stock1ClosePrices.length - 1];
        double stock2FirstClose = stock2ClosePrices[0];
        double stock2FinalClose = stock2ClosePrices[stock2ClosePrices.length - 1];


        boolean isDifferenceGreaterThan15 = Math.abs(stock1FirstClose - stock2FirstClose) > 15;

        VBox vbox = new VBox();

        if (isDifferenceGreaterThan15) {

            HBox hbox = new HBox(20);
            VBox stock1Box = new VBox();
            VBox stock2Box = new VBox();

            stock1Box.getChildren().add(createStockChart(stock1Symbol, stock1TimeIntervals, stock1ClosePrices, stock1FirstClose, stock1FinalClose));
            stock1Box.getChildren().add(createStockInfo(stock1Symbol, stock1Change, stock1FirstClose, stock1FinalClose));

            stock2Box.getChildren().add(createStockChart(stock2Symbol, stock2TimeIntervals, stock2ClosePrices, stock2FirstClose, stock2FinalClose));
            stock2Box.getChildren().add(createStockInfo(stock2Symbol, stock2Change, stock2FirstClose, stock2FinalClose));

            hbox.getChildren().addAll(stock1Box, stock2Box);
            vbox.getChildren().add(hbox);

        } else {

            LineChart<Number, Number> combinedChart = createStockChart(stock1Symbol, stock1TimeIntervals, stock1ClosePrices, stock1FirstClose, stock1FinalClose);
            combinedChart.getData().add(createStockSeries(stock2Symbol, stock2TimeIntervals, stock2ClosePrices));

            vbox.getChildren().add(combinedChart);


            vbox.getChildren().addAll(
                    createStockInfo(stock1Symbol, stock1Change, stock1FirstClose, stock1FinalClose),
                    createStockInfo(stock2Symbol, stock2Change, stock2FirstClose, stock2FinalClose)
            );
        }


        Stage stage = new Stage();
        stage.setTitle("Stock Price Comparison");

        Scene scene = new Scene(vbox);


        stage.setFullScreen(true);


        stage.setScene(scene);
        stage.show();
    }


    private LineChart<Number, Number> createStockChart(String stockSymbol, String[] timeIntervals, double[] closePrices, double firstClose, double finalClose) {
        double a;
        double b;
        if(firstClose > finalClose) {
            a= finalClose;
            b = firstClose;
        }else {
            a = firstClose;
            b = finalClose;
        }

        NumberAxis yAxis = new NumberAxis(a -1 , b +1, 0.05);
        yAxis.setLabel("Stock Price");


        NumberAxis xAxis = new NumberAxis(0, 26, 1);
        xAxis.setLabel("Time Interval (Starting from 9:30 AM to 16:00 in 15-min intervals)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(stockSymbol + " Stock Price");


        lineChart.getData().add(createStockSeries(stockSymbol, timeIntervals, closePrices));

        return lineChart;
    }


    private XYChart.Series<Number, Number> createStockSeries(String stockSymbol, String[] timeIntervals, double[] closePrices) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(stockSymbol);
        for (int i = 0; i < timeIntervals.length; i++) {
            series.getData().add(new XYChart.Data<>(i, closePrices[i]));
        }
        return series;
    }


    private VBox createStockInfo(String stockSymbol, double stockChange, double firstClose, double finalClose) {
        VBox stockInfoBox = new VBox(5);


        Text stockChangeText = new Text(stockSymbol + " Change: " + String.format("%.2f", stockChange) + "%");
        stockChangeText.setFill(stockChange >= 0 ? Color.GREEN : Color.RED);


        Text firstCloseText = new Text(stockSymbol + " First Close Price: " + String.format("%.2f", firstClose));
        Text finalCloseText = new Text(stockSymbol + " Final Close Price: " + String.format("%.2f", finalClose));


        stockInfoBox.getChildren().addAll(stockChangeText, firstCloseText, finalCloseText);
        return stockInfoBox;
    }


    private double calculatePercentageChange(double[] closePrices) {
        if (closePrices.length > 0) {
            double initialPrice = closePrices[0];
            double finalPrice = closePrices[closePrices.length - 1];
            return ((finalPrice - initialPrice) / initialPrice) * 100;
        }
        return 0;
    }
}
