package com.toj.jr;

import java.util.Calendar;

public class test {
    public String test() {
        String templatePath = this.getClass().getClassLoader().getResource("/Excel/record.xlsx").getPath();
        return templatePath;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
