package com.toj.mapper;

import com.toj.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ReportMapper {

    /**
     *
     * @param record
     * @return
     */
    public int insertReport(Record record);

    /**
     *
     * @param record
     * @return
     */
    public int updateReport(Record record);

    /**
     *
     * @param record
     * @return
     */
    public int updateByNameDate(Record record);

    /**
     *
     * @param name
     * @param date
     * @return
     */
    public Record selectReport(String name,String date);

    /**
     *
     * @param data
     * @return
     */
    public List<Record> selectReportList(Map<String, Object> data);

    /**
     *
     * @param id
     * @return
     */
    public Record getById(int id);

    /**
     *
     * @param id
     * @return
     */
    public int deleteReport(int id);
}
