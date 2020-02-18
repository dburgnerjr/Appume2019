package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class ClickListActivity extends Activity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    static int nPosition;

    private ListView lvActivityClickList;
    private TextView tvActivityClickListHeader;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_list_activity);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lvActivityClickList = findViewById(R.id.activityClickList);
        tvActivityClickListHeader = findViewById(R.id.activityClickListHeader);

        Intent intI = getIntent();
        if (intI == null) {
            closeOnError();
            return;
        }

        nPosition = intI.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (nPosition == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        populateUI(nPosition);
    }

    private void populateUI(int nPosition) {
        ArrayAdapter<String> adapter;
        String[] strActivityList;

        switch (nPosition) {
            case 3:
                strActivityList = getResources().getStringArray(R.array.android_projects);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityClickListHeader.setText(R.string.androidprojects);
                lvActivityClickList.setAdapter(adapter);
                lvActivityClickList.setOnItemClickListener((parent, view, position, id) -> launchAndroidProjectActivity(position));
                break;

            case 6:
                strActivityList = getResources().getStringArray(R.array.experience);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityClickListHeader.setText(R.string.experience);
                lvActivityClickList.setAdapter(adapter);
                lvActivityClickList.setOnItemClickListener((parent, view, position, id) -> launchExperienceActivity(position));
                break;

            case 7:
                strActivityList = getResources().getStringArray(R.array.recommendations);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityClickListHeader.setText(R.string.recommendations);
                lvActivityClickList.setAdapter(adapter);
                lvActivityClickList.setOnItemClickListener((parent, view, position, id) -> launchRecommendationActivity(position));
                break;

            case 8:
                strActivityList = getResources().getStringArray(R.array.contact_me);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityClickListHeader.setText(R.string.contactme);
                lvActivityClickList.setAdapter(adapter);
                lvActivityClickList.setOnItemClickListener((parent, view, position, id) -> launchContactMe(position));
                break;
        }
        setListViewHeightBasedOnChildren(lvActivityClickList);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void mainMenu() {
        Intent intI = new Intent(this, MainActivity.class);
        startActivity(intI);
        finish();
    }

    public boolean onKeyDown(int nKeyCode, KeyEvent keEvent) {
        if (nKeyCode == KeyEvent.KEYCODE_BACK) {
            mainMenu();
            return true;
        }
        return super.onKeyDown(nKeyCode, keEvent);
    }

    private void launchAndroidProjectActivity(int position) {
        Intent intent = new Intent(this, AndroidProjectsActivity.class);
        intent.putExtra(AndroidProjectsActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void launchExperienceActivity(int position) {
        Intent intent = new Intent(this, ExperienceActivity.class);
        intent.putExtra(ExperienceActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void launchRecommendationActivity(int position) {
        Intent intent = new Intent(this, RecommendationActivity.class);
        intent.putExtra(RecommendationActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void launchContactMe(int position) {
        ContactMeActivity cma = new ContactMeActivity();
        cma.launchContactMethod(this, position);
    }
}
