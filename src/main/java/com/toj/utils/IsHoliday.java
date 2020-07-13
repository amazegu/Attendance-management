package com.toj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IsHoliday {
    public String getWeek(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"日", "月", "火", "水", "木", "金", "土"};
        Calendar cal = Calendar.getInstance();
        int w = 0;
        try {
            Date tmpDate = format.parse(date);
            cal.setTime(tmpDate);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0){ w = 0; }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekDays[w];
    }
    public boolean isHoliday(List list,String date) {
        if(list.contains(date)){
            return true;
        }else if ("土".equals(getWeek(date))||"日".equals(getWeek(date))){
            return true;
        }else{
        return false;
        }
    }
}
