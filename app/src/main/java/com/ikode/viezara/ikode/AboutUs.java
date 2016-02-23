package com.ikode.viezara.ikode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {


    private TextView LICENSE;
    private TextView PRIVACY;
    private TextView TERMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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

        LICENSE = (TextView)findViewById(R.id.licensesUse);
        PRIVACY = (TextView) findViewById(R.id.privacyPolicy);
        TERMS = (TextView) findViewById(R.id.termsOfUse);

        LICENSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLicense = new Intent("android.intent.action.License");
                startActivity(toLicense);
            }
        });

        PRIVACY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPrivacy = new Intent("android.intent.action.PrivacyPolicy");
                startActivity(toPrivacy);
            }
        });

        TERMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTerms = new Intent("android.intent.action.Terms");
                startActivity(toTerms);
            }
        });






    }

}
