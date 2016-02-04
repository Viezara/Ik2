package com.ikode.viezara.ikode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;




public class Settings1 extends AppCompatActivity {

    //private TextView doneText;
    private ImageButton bProf, bReport, bDataStore;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings1);
/*
        if(RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){
            //doneText = (TextView) findViewById(R.id.tvDone);
            bProf = (ImageButton) findViewById(R.id.setBSettings);
            bReport = (ImageButton) findViewById(R.id.imgRep);
            bDataStore = (ImageButton) findViewById(R.id.imgSecDs);


          *//*  doneText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent TO_USERCHOICE = new Intent("android.intent.action.UserChoice");
                    startActivity(TO_USERCHOICE);

                }
            });*//*
        } else {
            Intent TO_LOGIN = new Intent(this, UserConnect.class);
            startActivity(TO_LOGIN);
        }*/
    }
}

















        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

