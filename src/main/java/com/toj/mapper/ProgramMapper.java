package com.toj.mapper;

import com.toj.pojo.Program;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramMapper {
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
     * @param data
     * @return
     */
    List<Program> selectProgram(Map<String, Object> data);

    /**
     *
     * @param data
     * @return
     */
    List<Program> getByPname(Map<String, Object> data);

    /**
     *
     * @param data
     * @return
     */
    List<Program> getById(Map<String, Object> data);

    /**
     *
     * @param eid
     * @return
     */
    Program getByEid(int eid);
}