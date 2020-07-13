package com.toj.pojo;

/**
 * @author stoned
 */
public class Employee {
	private int id;
	private int sex;
	private String name;
	private String userName;
	private String password;

    public Employee() {
    }

    public Employee(int id, int sex, String name, String userName, String password) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.userName = userName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() { return sex; }

    public void setSex(int sex) { this.sex = sex; }
}
