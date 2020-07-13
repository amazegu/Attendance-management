package com.toj.pojo;


public class Holiday {

  private int id;
  private String hDate;
  private String festival;

  public Holiday(int id, String hDate, String festival) {
    this.id = id;
    this.hDate = hDate;
    this.festival = festival;
  }

  public Holiday() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getHDate() {
    return hDate;
  }

  public void setHDate(String hDate) {
    this.hDate = hDate;
  }


  public String getFestival() {
    return festival;
  }

  public void setFestival(String festival) {
    this.festival = festival;
  }

}
