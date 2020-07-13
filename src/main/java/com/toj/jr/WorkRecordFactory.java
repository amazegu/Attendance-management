package com.toj.jr;

import com.toj.pojo.Mission;
import com.toj.pojo.Record;
import com.toj.service.MissionService;
import com.toj.utils.IsHoliday;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author stoned
 */
public class WorkRecordFactory {
    public static List<WorkRecordBean> getBeanCollection(List<Map> list,List hList, String s_date,String pname)

    {

// 数据源的生成，通常调用 dao 操作

        List data = new ArrayList();
        IsHoliday isHoliday = new IsHoliday();
        String[] s_arr = getStringDate(s_date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nDate = df.format(new Date());
        for(int i = 1;i<=Integer.parseInt(s_arr[3]);i++){
            getMap(list, hList, data, isHoliday, s_arr, nDate, i,pname);
        }
        return data;
    }

    public static List<WorkRecordBean> getBeanCollection(List<Map> list,List hList,String pname)
    {
// 数据源的生成，通常调用 dao 操作
        List data = new ArrayList();
        IsHoliday isHoliday = new IsHoliday();
        Calendar calendar = Calendar.getInstance();
        //now date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nDate = df.format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        String[] arr = getStringDate(date);
        for(int i = 1;i<=Integer.parseInt(arr[3]);i++){
            getMap(list, hList, data, isHoliday, arr, nDate, i,pname);
        }
        return data;
    }
    public static String[] getStringDate(String date){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date tmpDate = format.parse(date);
            calendar.setTime(tmpDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] arrDate = date.split("-");
        String[] arr = {arrDate[0],arrDate[1],arrDate[2],days+""};
        return  arr;
    }
    public static void getMap(List<Map> list, List hList, List data, IsHoliday isHoliday, String[] arr, String nDate, int i,String pname) {
        String day;
        Map map = new HashMap();
        day = String.valueOf(i);
        map.put("program",pname);
        map.put("day", day);
        map.put("month", arr[1]);
        map.put("year", arr[0]);
        map.put("nDate", nDate);
        String hDate = null;
        if (i < 10) {
            hDate = arr[0] + "-" + arr[1] + "-0" + day;
        }else{
            hDate = arr[0] + "-" + arr[1] + "-" + day;
        }
        if (isHoliday.isHoliday(hList, hDate)) {
            map.put("isHoliday", "休");
        }
        map.put("weekday", isHoliday.getWeek(hDate));
        for (Map m : list) {
            if (m.get("date").equals(hDate) || m.get("date").equals(hDate)) {
                map.put("startime", m.get("startime"));
                map.put("endtime", m.get("endtime"));
                map.put("worktime", m.get("worktime"));
                map.put("remarks", m.get("remarks"));
                map.put("situation", m.get("situation"));
                map.put("restime", m.get("rest"));
                map.put("design", m.get("design"));
                map.put("code", m.get("code"));
                map.put("test", m.get("test"));
                map.put("meeting", m.get("meeting"));
                map.put("study", m.get("study"));
                break;
            }
            map.put("name",m.get("name"));
        }
        data.add(map);
    }
}
