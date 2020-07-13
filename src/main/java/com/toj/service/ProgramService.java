package com.toj.service;

import com.toj.pojo.Program;

import java.util.List;

public interface ProgramService{
    /**
     *
     * @param program
     * @return
     */
    int insertProgram(Program program);

    /**
     *
     * @param program
     * @return
     */
    int updateProgram(Program program);

    /**
     *
     * @param id
     * @return
     */
    int deleteProgram(int id);

    /**
     *
     * @param name
     * @param endDate
     * @param startDate
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<Program> selectProgram(int pageNo, int pageSize,String name,String startDate,String endDate);


    /**
     *
     * @param pname
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<Program> getByPname(int pageNo, int pageSize,String pname);

    /**
     *
     * @param id
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<Program> getById(int pageNo, int pageSize,int id);

    /**
     *
     * @param eid
     * @return
     */
    Program getByEid(int eid);
}
