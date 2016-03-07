package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class License extends Activity {

    private Button licenseOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        licenseOkButton  = (Button) findViewById(R.id.licenseOkBtn);

        licenseOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy = true;
                onBackPressed();
            }
        });
    }


}
