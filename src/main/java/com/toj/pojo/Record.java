package com.toj.pojo;

/**
 * @author stoned
 */
public class Record {
    private int id;
    private String worktime;
    private int eid;
    private String remarks;
    private String name;
    private String date;
    private String startime;
    private String endtime;
    private String situation;
    private String rest;

    public Record() {
    }

    public Record(int id,String worktime, String name,int eid,String remarks, String date, String startime, String endtime, String situation, String rest) {
        this.id = id;
        this.worktime = worktime;
        this.name = name;
        this.date = date;
        this.startime = startime;
        this.endtime = endtime;
        this.situation = situation;
        this.eid = eid;
        this.remarks = remarks;
        this.rest = rest;
    }
    public Record(String worktime, String name,int eid,String remarks, String date, String startime, String endtime, String situation, String rest) {
        this.worktime = worktime;
        this.name = name;
        this.date = date;
        this.startime = startime;
        this.endtime = endtime;
        this.situation = situation;
        this.eid = eid;
        this.remarks = remarks;
        this.rest = rest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getWorktime() { return worktime; }

    public void setWorktime(String worktime) { this.worktime = worktime; }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRest() { return rest; }

    public void setRest(String rest) { this.rest = rest; }
}
