package com.toj.pojo;

public class Mission {
    private Integer id;
    private Integer eid;
    private String date;
    private String design;
    private String test;
    private String meeting;
    private String study;
    private String code;

    public Mission() {
    }

    public Mission(Integer id, Integer eid, String date, String design, String test, String meeting, String study, String code) {
        this.id = id;
        this.eid = eid;
        this.date = date;
        this.design = design;
        this.test = test;
        this.meeting = meeting;
        this.study = study;
        this.code = code;
    }

    public Mission(Integer eid, String date, String design, String test, String meeting, String study, String code) {
        this.eid = eid;
        this.date = date;
        this.design = design;
        this.test = test;
        this.meeting = meeting;
        this.study = study;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}