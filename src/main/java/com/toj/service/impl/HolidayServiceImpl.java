package com.toj.service.impl;

import com.toj.mapper.HolidayMapper;
import com.toj.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public List<String> selectAll() {
        return holidayMapper.selectAll();
    }
}
