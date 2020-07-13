package com.toj.service.impl;

import com.toj.mapper.RecordMapper;
import com.toj.pojo.Record;
import com.toj.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RecordServiceImpl implements RecordService, Serializable {

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 添加员工
     *
     * @param record 员工信息
     * @return 插入结果 !=0则插入成功
     */
    @Override
    public int insertRecord(Record record) {
        return recordMapper.insertRecord(record);
    }


    /**
     * 修改员工信息
     *
     * @param record 员工信息
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int modifyRecord(Record record) {
        return recordMapper.modifyRecord(record);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param endtime
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateEndtime(int eid, String date, String endtime) {
        return recordMapper.updateEndtime(eid, date, endtime);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param startime
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateStartime(int eid, String date, String startime) {
        return recordMapper.updateStartime(eid, date, startime);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param situation
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateSituation(int eid, String date, String situation) {
        return recordMapper.updateSituation(eid, date, situation);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param tasks
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateTasks(int eid, String date, String tasks) {
        return recordMapper.updateTasks(eid, date, tasks);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param rest
     * @param worktime
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateRest(int eid, String date, String rest, String worktime) {
        return recordMapper.updateRest(eid, date, rest, worktime);
    }

    /**
     * 修改记录
     *
     * @param eid
     * @param date
     * @param worktime
     * @return 修改结果 !=0则修改成功
     */
    @Override
    public int updateWorktime(int eid, String date, String worktime) {
        return recordMapper.updateWorktime(eid, date, worktime);
    }

    ;


    /**
     * 根据学号查询出员工实体
     *
     * @param name
     * @param date
     * @return
     */
    @Override
    public Record getByName(String name, String date) {
        return recordMapper.getByName(name, date);
    }


    /**
     * 查询全部员工
     *
     * @param pageNo
     * @param pageSize
     * @return 查询结果
     */
    @Override
    public List<Record> selectRecordBySql(int pageNo, int pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("pageNo", (pageNo - 1) * pageSize);
        data.put("pageSize", pageSize);
        return recordMapper.selectRecordBySql(data);
    }

    /**
     * 根据学号查询员工信息
     *
     * @param pageNo
     * @param pageSize
     * @return 查询结果
     */
    @Override
    public List<Record> getRecordBy(int pageNo, int pageSize, String name, String s_date, String e_date) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("pageNo", (pageNo - 1) * pageSize);
        data.put("pageSize", pageSize);
        data.put("name", name);
        data.put("s_date", s_date);
        data.put("e_date", e_date);
        return recordMapper.getRecordBy(data);
    }

    /**
     * 根据学号查询员工信息
     *
     * @param pageNo
     * @param pageSize
     * @return 查询结果
     */
    @Override
    public List<Record> getRecordByDate(int pageNo, int pageSize, String s_date, String e_date) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("pageNo", (pageNo - 1) * pageSize);
        data.put("pageSize", pageSize);
        data.put("s_date", s_date);
        data.put("e_date", e_date);
        return recordMapper.getRecordByDate(data);
    }

    /**
     * 根据学号查询员工信息
     *
     * @param pageNo
     * @param pageSize
     * @return 查询结果
     */
    @Override
    public List<Record> getByRecordId(int pageNo, int pageSize, int id) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("pageNo", (pageNo - 1) * pageSize);
        data.put("pageSize", pageSize);
        data.put("id", id);
        return recordMapper.getByRecordId(data);
    }

    /**
     * 根据学号查询员工信息
     *
     * @param pageNo
     * @param pageSize
     * @return 查询结果
     */
    @Override
    public List<Record> getRecordByName(int pageNo, int pageSize, String name) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("pageNo", (pageNo - 1) * pageSize);
        data.put("pageSize", pageSize);
        data.put("name", name);
        return recordMapper.getRecordByName(data);
    }


    /**
     * ajax验证是否存在
     *
     * @param name
     * @param date
     * @return 结果
     */
    @Override
    public Record isBeing(String name, String date) {
        return recordMapper.isBeing(name, date);
    }

    /**
     * @param condition
     * @param name
     * @return 结果
     */
    @Override
    public String getByDay(String name, String condition) {
        return recordMapper.getByDay(name, condition);
    }

}
