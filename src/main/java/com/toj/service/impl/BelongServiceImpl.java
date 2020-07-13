package com.toj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.toj.mapper.BelongMapper;
import com.toj.pojo.Belong;
import com.toj.service.BelongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BelongServiceImpl implements BelongService{
    @Autowired
    BelongMapper belongMapper;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        return belongMapper.deleteById(id);
    }

    /**
     *
     * @param pid
     * @return
     */
    @Override
    public int deleteByPid(int pid) {
        return belongMapper.deleteByPid(pid);
    }

    /**
     *
     * @param eid
     * @return
     */
    @Override
    public int deleteByEid(int eid) {
        return belongMapper.deleteByEid(eid);
    }

    /**
     *
     * @param eid
     * @param pid
     * @return
     */
    @Override
    public int insert(int eid, int pid) {
        return belongMapper.insert(eid,pid);
    }

    /**
     *
     * @param pid
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public List<Belong> selectByPid(int pageNo, int pageSize,int pid) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("pid",pid);
        return belongMapper.selectByPid(data);
    }

    /**
     *
     * @param eid
     * @param pid
     * @return
     */
    @Override
    public Belong selectByIds(int eid, int pid) {
        return belongMapper.selectByIds(eid,pid);
    }

    /**
     *
     * @param pid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Belong> selectDecidePid(int pageNo, int pageSize,int pid) {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("pageNo",(pageNo-1) * pageSize);
        data.put("pageSize",pageSize);
        data.put("pid",pid);
        return belongMapper.selectDecidePid(data);
    }
}
