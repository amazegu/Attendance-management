package com.toj.service.impl;


import com.toj.mapper.ReportMapper;
import com.toj.pojo.Record;
import com.toj.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    /**
     * 导入数据
     * @param record
     * @return
     */
    @Override
    public int insertReport(Record record) {
        return reportMapper.insertReport(record);
    }

    /**
     *
     * @param record
     * @return
     */
    @Override
    public int updateReport(Record record) {
        return reportMapper.updateReport(record);
    }

    /**
     *
     * @param record
     * @return
     */
    @Override
    public int updateByNameDate(Record record) {
        return reportMapper.updateByNameDate(record);
    }


    /**
     *
     * @param name
     * @param date
     * @return
     */
    @Override
    public Record selectReport(String name, String date) {
        return reportMapper.selectReport(name,date);
    }

    /**
     *
     * @param name
     * @param e_date
     * @param s_date
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<Record> selectReportList(int pageNo, int pageSize,String name,String s_date,String e_date) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("name",name);
        data.put("s_date",s_date);
        data.put("e_date",e_date);
        return reportMapper.selectReportList(data);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Record getById(int id){
        return reportMapper.getById(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteReport(int id) {
        return reportMapper.deleteReport(id);
    }
}