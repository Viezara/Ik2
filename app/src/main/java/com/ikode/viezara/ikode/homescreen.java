package com.ikode.viezara.ikode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class homescreen extends AppCompatActivity {

    private Button REGISTER_BTN;
    private Button SCAN_BTN;
    private ImageButton LOGIN_BTN;
    private TextView HELP;
    private TextView ABOUT;
    private CheckBox REGISTER_BOX;
    private View mViewOption;
    private TextView ikonalicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ikonalicense = (TextView) findViewById(R.id.ikonalicensetext);
        SpannableString ikltext = new SpannableString("licensed by Ikona®");

        ikltext.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ikonalicense.setMovementMethod(LinkMovementMethod.getInstance());
        ikonalicense.setText(ikltext);
        ikonalicense.setHighlightColor(Color.TRANSPARENT);


        REGISTER_BOX = (CheckBox) findViewById(R.id.checkBox2);

//        REGISTER_BTN = (Button) findViewById(R.id.registerBtn);
        SCAN_BTN  = (Button) findViewById(R.id.scanButton);
        LOGIN_BTN =(ImageButton) findViewById(R.id.loginButton1);
        View mViewOption = (View)findViewById(R.id.relativeLayout);
        HELP = (TextView) findViewById(R.id.textView26);
        ABOUT = (TextView) findViewById(R.id.textView27);
        //LOGIN_BTN.setImageResource(R.drawable.ic_login);

        if(!RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){

            //login picture

            LOGIN_BTN.setImageResource(R.drawable.ic_user);


      /*  }else{

            //exit picture

            LOGIN_BTN.setImageResource(R.drawable.ic_login);*/

        }

        //End application
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            //finish();
        }

        if (RequestData.user_Registered.equals("false")) {
            LOGIN_BTN.setVisibility(View.GONE);
            REGISTER_BOX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (REGISTER_BOX.isChecked()) {
                        Intent TO_REGISTRATION = new Intent("android.intent.action.REGISTRATION");
                        startActivity(TO_REGISTRATION);
                    }

                }
            });

        }
        else
        {
            mViewOption.setVisibility(View.GONE);

        }
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
                if(RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))) {
                    Intent userChoice = new Intent("android.intent.action.UserProfile");
                    startActivity(userChoice);
                }
                else{
                    Intent toLogin = new Intent("android.intent.action.UserConnect");
                    startActivity(toLogin);
                }

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

    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}
