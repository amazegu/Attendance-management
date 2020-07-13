package com.toj.utils;

import com.toj.pojo.Record;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    public void getWorktime(Record r) {
        if (StringUtils.isEmpty(r.getStartime())) {
            r.setStartime("00:00:00");
        }
        if (StringUtils.isEmpty(r.getEndtime())) {
            r.setEndtime("00:00:00");
        }
        String stime = r.getDate() + " " + r.getStartime();
        String etime = r.getDate() + " " + r.getEndtime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Float rest =0.0f;
        try {
            Date d1 = df.parse(stime);
            Date d2 = df.parse(etime);
            long diff = d2.getTime() - d1.getTime();
            if(!StringUtils.isEmpty(r.getRest())){
                rest = Float.parseFloat(r.getRest());
            }
            float worktime = diff / 1000 / 60 / 60 - rest;
            r.setWorktime(String.format("%.1f",worktime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String[] getNow(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = dateFormat.format( now );
        String [] dateTime = nowTime.split("\\s+");
        return dateTime;
    }
}
