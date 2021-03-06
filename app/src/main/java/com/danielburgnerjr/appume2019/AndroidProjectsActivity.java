package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielburgnerjr.appume2019.model.AndroidProject;
import com.danielburgnerjr.appume2019.utils.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;

import java.util.List;

public class AndroidProjectsActivity extends Activity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    static int nPosition;

    private TextView tvAppName;
    private ListView lvAppDescription;
    private AndroidProject andAP;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_projects_activity);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvAppName = findViewById(R.id.activityAndroidProjectsHeader);
        lvAppDescription = findViewById(R.id.activityAndroidProjectList);

        Intent intent = getIntent();
        if (intent == null)
            closeOnError();
        else
            nPosition = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (nPosition == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] strAndroidProject = getResources().getStringArray(R.array.android_project_details);
        String strJson = strAndroidProject[nPosition];

        try {
            andAP = JsonUtils.parseAndroidProjectJson(strJson);
            populateUI(andAP);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(AndroidProject andAP) {
        ArrayAdapter<String> aaAppDescription;
        List<String> strAppDescriptionList;

        strAppDescriptionList = andAP.getAppDescription();
        aaAppDescription = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strAppDescriptionList);

        tvAppName.setText(andAP.getAppName());
        lvAppDescription.setAdapter(aaAppDescription);
    }

    public void showGitHubAndroid(View view) {
        Intent newActivity = new Intent(Intent.ACTION_VIEW,  Uri.parse(andAP.getGitHubAndroid()));
        startActivity(newActivity);
    }

    public void showGitHubiOS(View view) {
        if (!andAP.getGitHubiOS().isEmpty()) {
            Intent newActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(andAP.getGitHubiOS()));
            startActivity(newActivity);
        } else {
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showPlayStore(View view) {
        if (!andAP.getPlayStore().isEmpty()) {
            Intent newActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(andAP.getPlayStore()));
            startActivity(newActivity);
        } else {
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showAppStore(View view) {
        if (!andAP.getAppStore().isEmpty()) {
            Intent newActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(andAP.getAppStore()));
            startActivity(newActivity);
        } else {
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
        }
    }

}
