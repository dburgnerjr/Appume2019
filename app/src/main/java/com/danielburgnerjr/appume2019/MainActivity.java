package com.danielburgnerjr.appume2019;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {

    ListView lvView;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, String.valueOf(R.string.admob_app_id));
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        String[] strMainMenu = getResources().getStringArray(R.array.main_menu);

        // Get ListView object from xml
        lvView = findViewById(R.id.appumeList);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - the Array of data

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strMainMenu);

        // Assign adapter to ListView
        lvView.setAdapter(adapter);

        // ListView Item Click Listener
        lvView.setOnItemClickListener((parent, view, position, id) -> {

            switch(position) {
                case 0:
                case 1:
                    launchDetailActivity(position);
                    break;

                case 2:
                case 4:
                case 5:
                    launchListActivity(position);
                    break;

                case 3:
                case 6:
                case 7:
                case 8:
                    launchClickListActivity(position);
                    break;
            }

        });
    }

    private void launchDetailActivity(int nPosition) {
        Intent intI = new Intent(this, DetailActivity.class);
        intI.putExtra(DetailActivity.EXTRA_POSITION, nPosition);
        startActivity(intI);
        finish();
    }

    private void launchListActivity(int nPosition) {
        Intent intI = new Intent(this, ListActivity.class);
        intI.putExtra(ListActivity.EXTRA_POSITION, nPosition);
        startActivity(intI);
        finish();
    }

    private void launchClickListActivity(int nPosition) {
        Intent intI = new Intent(this, ClickListActivity.class);
        intI.putExtra(ClickListActivity.EXTRA_POSITION, nPosition);
        startActivity(intI);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        AlertDialog adAlertBox = new AlertDialog.Builder(this).create();
        adAlertBox.setMessage("Do you want to exit application?");
        // do something when the button is clicked
        adAlertBox.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", (arg0, arg1) -> finish());
        // do something when the button is clicked
        adAlertBox.setButton(DialogInterface.BUTTON_NEGATIVE,"No", (arg0, arg1) -> { });
        adAlertBox.show();
    }
}
