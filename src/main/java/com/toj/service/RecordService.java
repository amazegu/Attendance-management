package com.toj.service;


import com.toj.pojo.Record;

import java.util.List;


public interface RecordService {


    /**
     * 添加记录
     *
     * @param record 记录
     * @return 插入结果 !=0则插入成功
     */
    public int insertRecord(Record record);


    /**
     * 修改记录
     *
     * @param record 员工信息
     * @return 修改结果 !=0则修改成功
     */
    public int modifyRecord(Record record);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param endtime
     * @return 修改结果 !=0则修改成功
     */
    public int updateEndtime(int eid, String date, String endtime);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param startime
     * @return 修改结果 !=0则修改成功
     */
    public int updateStartime(int eid, String date, String startime);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param situation
     * @return 修改结果 !=0则修改成功
     */
    public int updateSituation(int eid, String date, String situation);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param tasks
     * @return 修改结果 !=0则修改成功
     */
    public int updateTasks(int eid, String date, String tasks);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param rest
     * @param worktime
     * @return 修改结果 !=0则修改成功
     */
    public int updateRest(int eid, String date, String rest, String worktime);

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param worktime
     * @return 修改结果 !=0则修改成功
     */
    public int updateWorktime(int eid, String date, String worktime);


    /**
     * 根据用户名查询记录
     *
     * @param name
     * @param date
     * @return
     */
    public Record getByName(String name, String date);


    /**
     * sql后加limit实现分页
     */
    public List<Record> selectRecordBySql(int pageNo, int pageSize);

    /**
     * 根据学号查询记录
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @param e_date
     * @param s_date
     * @return 查询结果
     */
    public List<Record> getRecordBy(int pageNo, int pageSize, String name, String s_date, String e_date);

    /**
     * 根据学号查询记录
     *
     * @param pageNo
     * @param pageSize
     * @param e_date
     * @param s_date
     * @return 查询结果
     */
    public List<Record> getRecordByDate(int pageNo, int pageSize, String s_date, String e_date);

    /**
     * 根据学号查询记录
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @return 查询结果
     */
    public List<Record> getRecordByName(int pageNo, int pageSize, String name);

    /**
     * 根据学号查询员工信息
     *
     * @param pageNo
     * @param pageSize
     * @param id
     * @return 查询结果
     */
    public List<Record> getByRecordId(int pageNo, int pageSize, int id);


    /**
     * ajax验证是否存在
     *
     * @param name
     * @param date
     * @return 结果
     */
    public Record isBeing(String name, String date);

    /**
     * @param condition
     * @param name
     * @return 结果
     */
    public String getByDay(String name, String condition);


}
