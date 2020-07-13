package com.toj.pojo;

public class Belong {
    private Integer id;
    private Integer eid;
    private Integer pid;
    private String ename;
    private String pname;

    public Belong(Integer eid, Integer pid) {
        this.eid = eid;
        this.pid = pid;
    }

    public Belong() {
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getEid() { return eid; }

    public void setEid(Integer eid) { this.eid = eid; }

    public Integer getPid() { return pid; }

    public void setPid(Integer pid) { this.pid = pid; }

    public String getEname() { return ename; }

    public void setEname(String ename) { this.ename = ename; }

    public String getPname() { return pname; }

    public void setPname(String pname) { this.pname = pname; }
}