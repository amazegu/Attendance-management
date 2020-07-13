package com.toj.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.toj.mapper.MissionMapper;
import java.util.Date;
import com.toj.pojo.Mission;
import com.toj.service.MissionService;
@Service
public class MissionServiceImpl implements MissionService{

    @Resource
    private MissionMapper missionMapper;


    @Override
    public int deleteMission(int eid, String date) {
        return missionMapper.deleteMission(eid,date);
    }

    @Override
    public int insertMission(Mission misson) {
        return missionMapper.insertMission(misson);
    }

    @Override
    public int updateMission(Mission mission) { return missionMapper.updateMission(mission); }

    @Override
    public Mission selectMission(int eid, String date) {
        return missionMapper.selectMission(eid,date);
    }
}
