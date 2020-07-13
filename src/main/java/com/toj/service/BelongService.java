package com.toj.service;

import com.toj.pojo.Belong;

import java.util.List;

public interface BelongService{

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
    int insert(int eid, int pid);

    /**
     *
     * @param pid
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<Belong> selectByPid(int pageNo, int pageSize,int pid);


    /**
     *
     * @param pid
     * @param eid
     * @return
     */
    Belong selectByIds(int eid,int pid);

    /**
     *
     * @param pid
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<Belong> selectDecidePid(int pageNo, int pageSize,int pid);

}
