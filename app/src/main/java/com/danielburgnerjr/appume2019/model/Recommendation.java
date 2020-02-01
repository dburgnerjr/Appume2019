package com.danielburgnerjr.appume2019.model;

public class Recommendation {
    private String strManager;
    private String strRecommendationText;

    public Recommendation(String strMgr, String strRecTxt) {
        this.strManager = strMgr;
        this.strRecommendationText = strRecTxt;
    }

    public String getManager() {
        return strManager;
    }

    public String getRecommendationText() {
        return strRecommendationText;
    }
}
