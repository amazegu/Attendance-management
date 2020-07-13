package com.toj.service;

import com.toj.pojo.Mission;
public interface MissionService{


    int deleteMission(int eid,String date);

    int insertMission(Mission misson);

    int updateMission(Mission mission);

    Mission selectMission(int eid,String date);


}
