package com.danielburgnerjr.appume2019.model;

import java.util.List;

public class Experience {
    private String strPosition;
    private String strLocation;
    private String strCompany;
    private String strTimePeriod;
    private List<String> lstPositionDescription;

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

    public String getLocation() {
        return strLocation;
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
