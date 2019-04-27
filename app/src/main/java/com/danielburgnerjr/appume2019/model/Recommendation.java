package com.danielburgnerjr.appume2019.model;

public class Recommendation {
    private String strManager;
    private String strRecommendationText;

    /**
     * No args constructor for use in serialization
     */
    public Recommendation() {
    }

    public Recommendation(String strMgr, String strRecTxt) {
        this.strManager = strMgr;
        this.strRecommendationText = strRecTxt;
    }

    public String getManager() {
        return strManager;
    }

    public void setManager(String strMgr) {
        this.strManager = strMgr;
    }

    public String getRecommendationText() {
        return strRecommendationText;
    }

    public void setRecommendationText(String strRecTxt) {
        this.strRecommendationText = strRecTxt;
    }

}
