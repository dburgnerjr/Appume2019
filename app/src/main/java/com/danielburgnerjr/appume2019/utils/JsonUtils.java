package com.danielburgnerjr.appume2019.utils;

import com.danielburgnerjr.appume2019.model.Recommendation;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

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
