package com.danielburgnerjr.appume2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static int nPosition;

    private ListView lvActivityList;
    private TextView tvActivityListHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

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
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strActivityList);
                tvActivityListHeader.setText(R.string.certifications);
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
        //String strBackMessage = "Press Who Am I? to go to the previous screen, ";
        //strBackMessage += "Skills to go to the next screen or Main Menu to return to main menu.";
        if (nKeyCode == KeyEvent.KEYCODE_BACK) {
            mainMenu();
            //Toast.makeText(getApplicationContext(), strBackMessage, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(nKeyCode, keEvent);
    }

}