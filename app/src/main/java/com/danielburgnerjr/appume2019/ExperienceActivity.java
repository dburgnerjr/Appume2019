package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielburgnerjr.appume2019.model.Experience;
import com.danielburgnerjr.appume2019.utils.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import org.json.JSONException;

public class ExperienceActivity extends Activity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static int nPosition;

    private TextView tvPosition;
    private TextView tvLocation;
    private TextView tvCompany;
    private TextView tvTimePeriod;
    private ListView lvPositionDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience_activity);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvPosition = findViewById(R.id.activityExperiencePosition);
        tvLocation = findViewById(R.id.activityExperienceLocation);
        tvCompany  = findViewById(R.id.txtCompanyName);
        tvTimePeriod  = findViewById(R.id.txtTimePeriod);
        lvPositionDescription = findViewById(R.id.activityExperienceList);

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

        String[] strExperience = getResources().getStringArray(R.array.experience_details);
        String strJson = strExperience[nPosition];

        try {
            Experience expE = JsonUtils.parseExperienceJson(strJson);
            if (expE == null) {
                // Experience data unavailable
                closeOnError();
                return;
            }

            populateUI(expE);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Experience expE) {
        ArrayAdapter<String> aaPositionDescription;
        List<String> strExperienceDescriptionList;

        strExperienceDescriptionList = expE.getPositionDescription();
        aaPositionDescription = new ArrayAdapter<String>(this, R.layout.list_activity_text_view, strExperienceDescriptionList);

        tvPosition.setText(expE.getPosition());
        tvLocation.setText(expE.getLocation());
        tvCompany.setText(expE.getCompany());
        tvTimePeriod.setText(expE.getTimePeriod());
        setListViewHeightBasedOnChildren(lvPositionDescription);
        lvPositionDescription.setAdapter(aaPositionDescription);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
