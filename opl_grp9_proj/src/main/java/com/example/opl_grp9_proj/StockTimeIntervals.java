package com.example.opl_grp9_proj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StockTimeIntervals {

    // Generate time intervals from 9:15 AM to 3:15 PM at 15-minute intervals
    public static List<String> generateTimeIntervals() {
        List<String> timeIntervals = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        // Set the date to today's date and time to 9:15 AM
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm a"); // Formatting as "8/11 9:15 AM"

        // Generate time intervals from 9:15 AM to 3:15 PM
        while (calendar.get(Calendar.HOUR_OF_DAY) < 15 || (calendar.get(Calendar.HOUR_OF_DAY) == 15 && calendar.get(Calendar.MINUTE) <= 15)) {
            timeIntervals.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 15); // Increment by 15 minutes
        }
        return timeIntervals;
    }
}
