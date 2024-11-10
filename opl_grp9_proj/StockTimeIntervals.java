package com.example.opl_grp9_proj;

import java.util.ArrayList;
import java.util.List;

public class StockTimeIntervals {

    // Method to generate the time intervals from 9:30 AM to 4:00 PM with 15-minute gaps
    public static List<String> generateTimeIntervals() {
        List<String> timeIntervals = new ArrayList<>();

        // Start time: 9:30 AM
        int startHour = 9;
        int startMinute = 30;

        // Loop through time from 9:30 AM to 4:00 PM
        for (int i = 0; i < 26; i++) {  // 26 intervals in total
            String formattedTime = String.format("%02d:%02d", startHour, startMinute);
            timeIntervals.add(formattedTime);

            // Increment time by 15 minutes
            startMinute += 15;
            if (startMinute == 60) {
                startMinute = 0;
                startHour++;
            }
        }

        return timeIntervals;
    }
}
