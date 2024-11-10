package com.example.opl_grp9_proj;

public class StockData {
    private String[] timeIntervals;
    private double[] closePrices;

    // Constructor to initialize with timeIntervals and closePrices arrays
    public StockData(String[] timeIntervals, double[] closePrices) {
        this.timeIntervals = timeIntervals;
        this.closePrices = closePrices;
    }

    // Getter for time intervals
    public String[] getTimeIntervals() {
        return timeIntervals;
    }

    // Getter for close prices
    public double[] getClosePrices() {
        return closePrices;
    }
}
