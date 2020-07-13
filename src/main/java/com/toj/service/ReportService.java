
package com.toj.service;

import com.toj.pojo.Record;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ReportService {
    /**
     * @param record
     * 导入数据
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
     * @param s_date
     * @param e_date
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Record> selectReportList(int pageNo, int pageSize,String name, String s_date, String e_date);

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

