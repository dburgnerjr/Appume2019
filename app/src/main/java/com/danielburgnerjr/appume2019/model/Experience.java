package com.danielburgnerjr.appume2019.model;

import java.util.List;

public class Experience {
    private String strPosition;
    private String strLocation;
    private String strCompany = null;
    private String strTimePeriod = null;
    private List<String> lstPositionDescription = null;

    /**
     * No args constructor for use in serialization
     */
    public Experience() {
    }

    public Experience(String strPos, String strLoc, String strCom, String strTP, List<String> lstPD) {
        this.strPosition = strPos;
        this.strLocation = strLoc;
        this.strCompany = strCom;
        this.strTimePeriod = strTP;
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

    public String getCompany() {
        return strCompany;
    }

    public String getTimePeriod() {
        return strTimePeriod;
    }

    public List<String> getPositionDescription() {
        return lstPositionDescription;
    }
}
