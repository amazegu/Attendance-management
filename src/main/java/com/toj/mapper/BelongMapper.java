package com.toj.mapper;

import com.toj.pojo.Belong;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BelongMapper {
    /**
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     *
     * @param pid
     * @return
     */
    int deleteByPid(int pid);

    /**
     *
     * @param eid
     * @return
     */
    int deleteByEid(int eid);

    /**
     *
     * @param eid
     * @param pid
     * @return
     */
    int insert(@Param("eid") int eid,@Param("pid") int pid);

    /**
     *
     * @param data
     * @return
     */
    List<Belong> selectByPid(Map<String, Object> data);

    /**
     *
     * @param pid
     * @param eid
     * @return
     */
    Belong selectByIds(@Param("eid") int eid,@Param("pid") int pid);

    /**
     *
     * @param data
     * @return
     */
    List<Belong> selectDecidePid(Map<String, Object> data);

}
