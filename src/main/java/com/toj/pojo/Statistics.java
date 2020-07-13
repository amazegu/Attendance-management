package com.toj.pojo;

public class Statistics {
    private String name;
    private float wtime;
    private float statime;

    public Statistics() {
    }

    public Statistics(String name, float wtime, float statime) {
        this.name = name;
        this.wtime = wtime;
        this.statime = statime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWtime() {
        return wtime;
    }

    public void setWtime(float wtime) {
        this.wtime = wtime;
    }

    public float getStatime() {
        return statime;
    }

    public void setStatime(float statime) {
        this.statime = statime;
    }

}
