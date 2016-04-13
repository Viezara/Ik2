package com.ikode.viezara.ikode;

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
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {


    private ImageButton LICENSE;
    private ImageButton PRIVACY;
    private ImageButton TERMS;
    private TextView ikonalicenseabout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ikonalicenseabout = (TextView) findViewById(R.id.iklabtTxt);
        SpannableString iklabouttext = new SpannableString("licensed by Ikona®");

        iklabouttext.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        iklabouttext.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        iklabouttext.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ikonalicenseabout.setMovementMethod(LinkMovementMethod.getInstance());
        ikonalicenseabout.setText(iklabouttext);
        ikonalicenseabout.setHighlightColor(Color.TRANSPARENT);


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

        LICENSE = (ImageButton) findViewById(R.id.licensesUse);
        PRIVACY = (ImageButton) findViewById(R.id.privacyPolicy);
        TERMS = (ImageButton) findViewById(R.id.termsOfUse);

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
