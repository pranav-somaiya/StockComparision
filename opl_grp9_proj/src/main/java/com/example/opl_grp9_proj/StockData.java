package com.example.opl_grp9_proj;

public class StockData {
    private String[] timeIntervals;
    private double[] closePrices;


    public StockData(String[] timeIntervals, double[] closePrices) {
        this.timeIntervals = timeIntervals;
        this.closePrices = closePrices;
    }


    public String[] getTimeIntervals() {
        return timeIntervals;
    }


    public double[] getClosePrices() {
        return closePrices;
    }
}
