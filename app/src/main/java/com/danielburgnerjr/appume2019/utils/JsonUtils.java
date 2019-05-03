package com.danielburgnerjr.appume2019.utils;

import com.danielburgnerjr.appume2019.model.AndroidProject;
import com.danielburgnerjr.appume2019.model.Experience;
import com.danielburgnerjr.appume2019.model.Recommendation;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static AndroidProject parseAndroidProjectJson(String json) throws JSONException {

        String strAppName;
        String strGitHubAndroid;
        String strPlayStore = null;
        String strGitHubiOS = null;
        String strAppStore = null;
        List<String> lstAppDescription = null;

        JSONObject jsonObject = new JSONObject(json);
        JSONObject name = jsonObject.getJSONObject("name");
        strAppName = name.getString("app_name");
        strGitHubAndroid = name.getString("github_android_link");
        strPlayStore = name.getString("play_store_link");
        strGitHubiOS = name.getString("github_ios_link");
        strAppStore = name.getString("app_store_link");

        lstAppDescription = new ArrayList<String>();
        if (name.has("app_description")) {
            JSONArray jsnaAppDescription = name.getJSONArray("app_description");

            //Iterate through the array of jsnaPositionDescription and add it to list
            for (int nI = 0; nI < jsnaAppDescription.length(); nI++) {
                String strAppDescription = jsnaAppDescription.getString(nI);
                lstAppDescription.add(strAppDescription);
            }
        }

        AndroidProject andAP = new AndroidProject(strAppName, strGitHubAndroid, strPlayStore, strGitHubiOS, strAppStore, lstAppDescription);
        return andAP;
    }

    public static Experience parseExperienceJson(String json) throws JSONException {

        String strPosition;
        String strLocation;
        String strCompany = null;
        String strTimePeriod = null;
        List<String> lstPositionDescription = null;

        JSONObject jsonObject = new JSONObject(json);
        JSONObject name = jsonObject.getJSONObject("name");
        strPosition = name.getString("position");
        strLocation = name.getString("location");
        strCompany = name.getString("company");
        strTimePeriod = name.getString("time_period");

        lstPositionDescription = new ArrayList<String>();
        if (name.has("position_description")) {
            JSONArray jsnaPositionDescription = name.getJSONArray("position_description");

            //Iterate through the array of jsnaPositionDescription and add it to list
            for (int nI = 0; nI < jsnaPositionDescription.length(); nI++) {
                String strPositionDescription = jsnaPositionDescription.getString(nI);
                lstPositionDescription.add(strPositionDescription);
            }
        }

        Experience expE = new Experience(strPosition, strLocation, strCompany, strTimePeriod, lstPositionDescription);
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
