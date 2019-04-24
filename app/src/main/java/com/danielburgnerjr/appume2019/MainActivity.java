package com.danielburgnerjr.appume2019;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView lvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] strMainMenu = getResources().getStringArray(R.array.main_menu);

        // Get ListView object from xml
        lvView = (ListView) findViewById(R.id.appumeList);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - the Array of data

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, R.layout.list_activity_text_view, strMainMenu);

        // Assign adapter to ListView
        lvView.setAdapter(adapter);

        // ListView Item Click Listener

        lvView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) lvView.getItemAtPosition(position);

                Intent newActivity;

                switch(position) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
                        //newActivity = new Intent(Appume.this, WhatIsAppume.class);
                        //startActivity(newActivity);
                        //finish();
                        break;

                    case 1:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
                        //newActivity = new Intent(Appume.this, WhoAmI.class);
                        //startActivity(newActivity);
                        //finish();
                        break;

                    case 2:
                    case 4:
                    case 5:
                        launchListActivity(position);
                        break;

                    case 3:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
/*
                        newActivity = new Intent(Appume.this, Skills.class);
                        startActivity(newActivity);
                        finish();
*/
/*
                        newActivity = new Intent(Appume.this, Experience.class);
                        startActivity(newActivity);
                        finish();
*/
                        break;

                    case 6:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
/*
                        newActivity = new Intent(Appume.this, Recommendations.class);
                        startActivity(newActivity);
                        finish();
*/
                        break;

                    case 7:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
/*
                        newActivity = new Intent(Appume.this, ContactMe.class);
                        startActivity(newActivity);
                        finish();
*/
                        break;

                    case 8:
                        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_LONG).show();
/*
                        newActivity = new Intent(Appume.this, ContactMe.class);
                        startActivity(newActivity);
                        finish();
*/
                        break;

                }

            }

        });

    }

    private void launchListActivity(int nPosition) {
        Intent intI = new Intent(this, ListActivity.class);
        intI.putExtra(ListActivity.EXTRA_POSITION, nPosition);
        startActivity(intI);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.appume, menu);
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        AlertDialog adAlertBox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        //close();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
}
