package com.danielburgnerjr.appume2019;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.danielburgnerjr.appume2019.model.Recommendation;
import com.danielburgnerjr.appume2019.utils.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;

public class RecommendationActivity extends Activity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static int nPosition;

    private TextView tvManager;
    private TextView tvRecommendationText;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_activity);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvManager = findViewById(R.id.activityRecommendationHeader);
        tvRecommendationText = findViewById(R.id.activityRecommendationText);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        nPosition = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (nPosition == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] strRecommendations = getResources().getStringArray(R.array.recommendation_details);
        String strJson = strRecommendations[nPosition];
        try {
            Recommendation recR = JsonUtils.parseRecommendationJson(strJson);
            if (recR == null) {
                // Recommendation data unavailable
                closeOnError();
                return;
            }

            populateUI(recR);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Recommendation recR) {
        tvManager.setText(recR.getManager());
        tvRecommendationText.setText(recR.getRecommendationText());
    }
}
