package com.example.opl_grp9_proj;

import java.util.ArrayList;
import java.util.List;

public class StockTimeIntervals {


    public static List<String> generateTimeIntervals() {
        List<String> timeIntervals = new ArrayList<>();


        int startHour = 9;
        int startMinute = 30;


        for (int i = 0; i < 26; i++) {
            String formattedTime = String.format("%02d:%02d", startHour, startMinute);
            timeIntervals.add(formattedTime);


            startMinute += 15;
            if (startMinute == 60) {
                startMinute = 0;
                startHour++;
            }
        }

        return timeIntervals;
    }
}
