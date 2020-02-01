package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ListActivity extends Activity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    static int nPosition;

    private ListView lvActivityList;
    private TextView tvActivityListHeader;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lvActivityList = findViewById(R.id.activityList);
        tvActivityListHeader = findViewById(R.id.activityListHeader);

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
            case 2:
                strActivityList = getResources().getStringArray(R.array.certifications);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityListHeader.setText(R.string.certifications);
                lvActivityList.setAdapter(adapter);
                break;

            case 4:
                strActivityList = getResources().getStringArray(R.array.skills);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityListHeader.setText(R.string.skills);
                lvActivityList.setAdapter(adapter);
                break;

            case 5:
                strActivityList = getResources().getStringArray(R.array.education);
                adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strActivityList);
                tvActivityListHeader.setText(R.string.education);
                lvActivityList.setAdapter(adapter);
                break;
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
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

}
