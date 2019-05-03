package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielburgnerjr.appume2019.model.AndroidProject;
import com.danielburgnerjr.appume2019.model.Experience;
import com.danielburgnerjr.appume2019.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class AndroidProjectsActivity extends Activity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static int nPosition;

    private TextView tvAppName;
    private TextView tvGitHubAndroid;
    private TextView tvPlayStore;
    private TextView tvGitHubiOS;
    private TextView tvAppStore;
    private ListView lvAppDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_projects_activity);

        tvAppName = findViewById(R.id.activityAndroidProjectsHeader);
        tvGitHubAndroid = findViewById(R.id.activityGitHubRepoAndroid);
        tvPlayStore  = findViewById(R.id.activityGooglePlayStore);
        tvGitHubiOS  = findViewById(R.id.activityGitHubRepoIOS);
        tvAppStore  = findViewById(R.id.activityAppStore);
        lvAppDescription = findViewById(R.id.activityAndroidProjectList);

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

        String[] strAndroidProject = getResources().getStringArray(R.array.android_project_details);
        String strJson = strAndroidProject[nPosition];

        try {
            AndroidProject andAP = JsonUtils.parseAndroidProjectJson(strJson);
            if (andAP == null) {
                // AndroidProject data unavailable
                closeOnError();
                return;
            }

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
        aaAppDescription = new ArrayAdapter<String>(this, R.layout.list_activity_text_view, strAppDescriptionList);

        tvAppName.setText(andAP.getAppName());
        tvGitHubAndroid.setText(andAP.getGitHubAndroid());
        tvPlayStore.setText(andAP.getPlayStore());
        tvGitHubiOS.setText(andAP.getGitHubiOS());
        tvAppStore.setText(andAP.getAppStore());
        lvAppDescription.setAdapter(aaAppDescription);
    }
}
