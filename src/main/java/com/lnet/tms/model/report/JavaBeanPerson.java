package com.lnet.tms.model.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/6.
 */
public class JavaBeanPerson {
    private String USERNAME;
    private String EMAIL;
    private int FULL_NAME;


    public JavaBeanPerson() {
    }

    public JavaBeanPerson(String USERNAME, String EMAIL, int FULL_NAME) {
        this.USERNAME = USERNAME;
        this.EMAIL = EMAIL;
        this.FULL_NAME = FULL_NAME;
    }

    public int getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(int FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public static List<JavaBeanPerson> getList() {
        List<JavaBeanPerson> list = new ArrayList<>();
        list.add(new JavaBeanPerson("Lily", "female", 22));
        list.add(new JavaBeanPerson("Macro", "male", 33));
        list.add(new JavaBeanPerson("Andy", "male", 44));
        list.add(new JavaBeanPerson("Linder", "female", 28));
        list.add(new JavaBeanPerson("Jessie", "female", 26));
        return list;
    }
}
