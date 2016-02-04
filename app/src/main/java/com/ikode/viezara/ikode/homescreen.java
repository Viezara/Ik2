package com.ikode.viezara.ikode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homescreen extends AppCompatActivity {

    private Button REGISTER_BTN;
    private Button SCAN_BTN;
    private Button LOGIN_BTN;
    private ImageButton HELP;
    private ImageButton ABOUT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        REGISTER_BTN = (Button) findViewById(R.id.registerBtn);
        SCAN_BTN  = (Button) findViewById(R.id.scanButton);
        LOGIN_BTN =(Button) findViewById(R.id.btnLogin);
        HELP = (ImageButton) findViewById(R.id.imageButton2);
        ABOUT = (ImageButton) findViewById(R.id.imageButton);

        REGISTER_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TO_REGISTRATION = new Intent("android.intent.action.REGISTRATION");
                startActivity(TO_REGISTRATION);
            }
        });

        SCAN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TO_REGISTRATION = new Intent("android.intent.action.CAPTURE");
                startActivity(TO_REGISTRATION);
            }
        });

        LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent("android.intent.action.UserConnect");
                startActivity(toLogin);
            }
        });

        HELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelp = new Intent("android.intent.action.HelpPage");
                startActivity(toHelp);
            }
        });

        ABOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelp = new Intent("android.intent.action.AboutUs");
                startActivity(toHelp);

            }
        });
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
    }

}
