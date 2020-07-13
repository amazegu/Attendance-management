package com.toj.service.impl;

import com.toj.mapper.ProgramMapper;
import com.toj.pojo.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toj.service.ProgramService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    ProgramMapper programMapper;

    /**
     *
     * @param program
     * @return
     */
    @Override
    public int insertProgram(Program program) {
        return programMapper.insertProgram(program);
    }

    /**
     *
     * @param program
     * @return
     */
    @Override
    public int updateProgram(Program program) {
        return programMapper.updateProgram(program);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteProgram(int id) {
        return programMapper.deleteProgram(id);
    }

    /**
     *
     * @param name
     * @param startDate
     * @param endDate
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<Program> selectProgram(int pageNo, int pageSize,String name, String startDate, String endDate) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("name",name);
        data.put("startDate",startDate);
        data.put("endDate",endDate);
        return programMapper.selectProgram(data);
    }

    /**
     *
     * @param pname
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<Program> getByPname(int pageNo, int pageSize,String pname) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("pname",pname);
        return programMapper.getByPname(data);
    }

    /**
     *
     * @param id
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<Program> getById(int pageNo, int pageSize,int id) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("id",id);
        return programMapper.getById(data);
    }

    /**
     * @param eid
     * @return
     */
    @Override
    public Program getByEid(int eid) {
        return programMapper.getByEid(eid);
    }

}
