package com.danielburgnerjr.appume2019.model;

import java.util.List;

public class Experience {
    private String strPosition;
    private String strLocation;
    private List<String> lstCompany = null;
    private List<String> lstTimePeriod = null;
    private List<String> lstPositionDescription = null;

    /**
     * No args constructor for use in serialization
     */
    public Experience() {
    }

    public Experience(String strPos, String strLoc, List<String> lstCom, List<String> lstTP, List<String> lstPD) {
        this.strPosition = strPos;
        this.strLocation = strLoc;
        this.lstCompany = lstCom;
        this.lstTimePeriod = lstTP;
        this.lstPositionDescription = lstPD;
    }

    public String getPosition() {
        return strPosition;
    }

    public void setPosition(String strPos) {
        this.strPosition = strPos;
    }

    public String getLocation() {
        return strLocation;
    }

    public void setLocation(String strLoc) {
        this.strLocation = strLoc;
    }

    public List<String> getCompany() {
        return lstCompany;
    }

    public List<String> getTimePeriod() {
        return lstTimePeriod;
    }

    public List<String> getPositionDescription() {
        return lstPositionDescription;
    }
}
