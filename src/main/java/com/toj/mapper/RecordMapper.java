package com.toj.mapper;

import com.toj.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface RecordMapper {


    /**
     *  添加记录
     * @param record   记录
     * @return  插入结果 !=0则插入成功
     */
    public int insertRecord(Record record);


    /**
     *  修改记录
     * @param record   员工信息
     * @return  修改结果 !=0则修改成功
     */
    public int modifyRecord(Record record);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param endtime
     * @return  修改结果 !=0则修改成功
     */
    public int updateEndtime(@Param("eid") int eid,@Param("date") String date,@Param("endtime") String endtime);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param startime
     * @return  修改结果 !=0则修改成功
     */
    public int updateStartime(@Param("eid") int eid,@Param("date") String date,@Param("startime") String startime);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param situation
     * @return  修改结果 !=0则修改成功
     */
    public int updateSituation(@Param("eid") int eid,@Param("date") String date,@Param("situation") String situation);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param tasks
     * @return  修改结果 !=0则修改成功
     */
    public int updateTasks(@Param("eid") int eid,@Param("date") String date,@Param("tasks") String tasks);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param rest
     * @param worktime
     * @return  修改结果 !=0则修改成功
     */
    public int updateRest(@Param("eid") int eid,@Param("date") String date,@Param("rest") String rest,@Param("worktime") String worktime);

    /**
     *  修改记录
     * @param eid
     * @param date
     * @param worktime
     * @return  修改结果 !=0则修改成功
     */
    public int updateWorktime(@Param("eid") int eid,@Param("date") String date,@Param("worktime") String worktime);

    /**
     *  根据用户名查询记录
     * @param name
     * @param date
     * @return
     */
    public Record getByName(@Param("name") String name,@Param("date") String date);



    /**
     * 查询全部记录，接住sql语句进行分页
     * @param data
     * @return      查询结果
     */
    public List<Record> selectRecordBySql(Map<String, Object> data);

    /**
     * 根据用户名查询记录
     * @param data
     * @return  查询结果
     */
    public List<Record> getRecordBy(Map<String, Object> data);

    /**
     * 根据用户名查询记录
     * @param data
     * @return  查询结果
     */
    public List<Record> getRecordByDate(Map<String, Object> data);

    /**
     * 根据用户名查询记录
     * @param data
     * @return  查询结果
     */
    public List<Record> getRecordByName(Map<String, Object> data);

    /**
     * 根据用户名查询记录
     * @param data
     * @return  查询结果
     */
    public List<Record> getByRecordId(Map<String, Object> data);



    /**
     *  ajax验证记录是否存在
     * @param name
     * @param date
     * @return  Record
     */
    public Record isBeing(@Param("name") String name, @Param("date") String date);

    /**
     * @param name
     * @param condition
     * @return  结果
     */
    public String getByDay(@Param("name") String name, @Param("condition") String condition);

}
