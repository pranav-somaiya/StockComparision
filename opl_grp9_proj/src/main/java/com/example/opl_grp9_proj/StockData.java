package com.example.opl_grp9_proj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockData {

    private String dateTime;  // Date and time in string format (e.g., "2024-11-08 09:15 AM")
    private double closePrice;

    // Constructor to initialize the StockData object
    public StockData(String dateTime, double closePrice) {
        this.dateTime = dateTime;
        this.closePrice = closePrice;
    }

    // Getter method for the date and time
    public String getDateTime() {
        return dateTime;
    }

    // Getter method for the close price
    public double getClosePrice() {
        return closePrice;
    }

    // Convert the date-time string to a timestamp (milliseconds)
    public long getDateAsTimestamp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");  // Adjusted format to include time
        Date parsedDate = sdf.parse(dateTime);  // Parse the string to Date
        return parsedDate.getTime();  // Return the timestamp in milliseconds
    }

    // Optional: To return stock data in a more readable form (e.g., for debugging or logging)
    @Override
    public String toString() {
        return "StockData{" +
                "dateTime='" + dateTime + '\'' +
                ", closePrice=" + closePrice +
                '}';
    }
}
