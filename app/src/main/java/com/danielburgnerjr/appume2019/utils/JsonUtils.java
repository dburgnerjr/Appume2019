package com.danielburgnerjr.appume2019.utils;

import com.danielburgnerjr.appume2019.model.Experience;
import com.danielburgnerjr.appume2019.model.Recommendation;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Experience parseExperienceJson(String json) throws JSONException {

        String strPosition;
        String strLocation;
        List<String> lstCompany = null;
        List<String> lstTimePeriod = null;
        List<String> lstPositionDescription = null;

        JSONObject jsonObject = new JSONObject(json);
        JSONObject name = jsonObject.getJSONObject("name");
        strPosition = name.getString("position");
        strLocation = name.getString("location");

        lstCompany = new ArrayList<String>();
        if (name.has("company")) {
            JSONArray jsnaCompany = name.getJSONArray("company");

            //Iterate through the array of jsnaCompany and add it to list
            for (int nI = 0; nI < jsnaCompany.length(); nI++) {
                String strCompany = jsnaCompany.getString(nI);
                lstCompany.add(strCompany);
            }
        }

        lstTimePeriod = new ArrayList<String>();
        if (name.has("time_period")) {
            JSONArray jsnaTimePeriod = name.getJSONArray("time_period");

            //Iterate through the array of jsnaTimePeriod and add it to list
            for (int nI = 0; nI < jsnaTimePeriod.length(); nI++) {
                String strTimePeriod = jsnaTimePeriod.getString(nI);
                lstTimePeriod.add(strTimePeriod);
            }
        }

        lstPositionDescription = new ArrayList<String>();
        if (name.has("position_description")) {
            JSONArray jsnaPositionDescription = name.getJSONArray("position_description");

            //Iterate through the array of jsnaPositionDescription and add it to list
            for (int nI = 0; nI < jsnaPositionDescription.length(); nI++) {
                String strPositionDescription = jsnaPositionDescription.getString(nI);
                lstPositionDescription.add(strPositionDescription);
            }
        }

        Experience expE = new Experience(strPosition, strLocation, lstCompany, lstTimePeriod, lstPositionDescription);
        return expE;
    }

    public static Recommendation parseRecommendationJson(String json) throws JSONException {

        String strManager;
        String strRecommendationText;

        JSONObject jsonObject = new JSONObject(json);
        JSONObject name = jsonObject.getJSONObject("name");
        strManager = name.getString("manager");
        strRecommendationText = name.getString("recommendationText");

        Recommendation recR = new Recommendation(strManager, strRecommendationText);
        return recR;
    }
}
