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

import com.danielburgnerjr.appume2019.adapter.CompanyTimePeriodAdapter;
import com.danielburgnerjr.appume2019.model.Experience;
import com.danielburgnerjr.appume2019.utils.JsonUtils;

import java.util.List;

import org.json.JSONException;

public class ExperienceActivity extends Activity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static int nPosition;

    private TextView tvPosition;
    private TextView tvLocation;
    private ListView lvCompanyTimePeriod;
    private ListView lvPositionDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience_activity);

        tvPosition = (TextView) findViewById(R.id.activityExperiencePosition);
        tvLocation = (TextView) findViewById(R.id.activityExperienceLocation);
        lvCompanyTimePeriod = findViewById(R.id.activityCompanyTimePeriodList);
        lvPositionDescription = findViewById(R.id.activityExperienceList);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        if (nPosition == DEFAULT_POSITION) {
        nPosition = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

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
        CompanyTimePeriodAdapter ctpCompanyNameTimePdList;
        ArrayAdapter<String> aaPositionDescription;
        List<String> strCompanyNameList;
        List<String> strTimePeriodList;
        List<String> strExperienceDescriptionList;

        strCompanyNameList = expE.getCompany();
        strTimePeriodList = expE.getTimePeriod();
        strExperienceDescriptionList = expE.getPositionDescription();
        ctpCompanyNameTimePdList = new CompanyTimePeriodAdapter(strCompanyNameList, strTimePeriodList);
        aaPositionDescription = new ArrayAdapter<String>(this, R.layout.list_activity_text_view, strExperienceDescriptionList);

        tvPosition.setText(expE.getPosition());
        tvLocation.setText(expE.getLocation());
        //lvCompanyTimePeriod.setAdapter(ctpCompanyNameTimePdList);
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
