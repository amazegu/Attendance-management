package com.toj.mapper;

import com.toj.pojo.Mission;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface MissionMapper {
    /**
     *
     * @param eid
     * @param date
     * @return
     */
    int deleteMission(@Param("eid") int eid,@Param("date") String date);

    /**
     *
     * @param misson
     * @return
     */
    int insertMission(Mission misson);

    /**
     *
     * @param mission
     * @return
     */
    int updateMission(Mission mission);

    /**
     *
     * @param eid
     * @param date
     * @return
     */
    Mission selectMission(@Param("eid") int eid,@Param("date") String date);
}