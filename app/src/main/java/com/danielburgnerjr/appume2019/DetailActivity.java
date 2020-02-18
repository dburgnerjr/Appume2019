package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DetailActivity extends Activity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    static int nPosition;

    private TextView tvDetailedInfo;
    private TextView tvDetailedInfoHeader;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvDetailedInfo = findViewById(R.id.detailedInfo);
        tvDetailedInfoHeader = findViewById(R.id.activityDetailHeader);

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

        switch (nPosition) {
            case 0:
                tvDetailedInfoHeader.setText(R.string.whatisappume);
                tvDetailedInfo.setText(R.string.whatisappumedetail);
                break;

            case 1:
                tvDetailedInfoHeader.setText(R.string.whoami);
                tvDetailedInfo.setText(R.string.whoamidetail);
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
