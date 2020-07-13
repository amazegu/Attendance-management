package com.toj.pojo;

import java.util.Date;

public class Program {

    private Integer id;
    private String name;
    private String startime;
    private String endtime;
    private String rest;
    private String adress;
    private String startDate;
    private String endDate;

    public Program(String name, String startime, String endtime, String rest, String adress, String startDate, String endDate) {
        this.name = name;
        this.startime = startime;
        this.endtime = endtime;
        this.rest = rest;
        this.adress = adress;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Program() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}

