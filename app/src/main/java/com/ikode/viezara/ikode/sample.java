package com.ikode.viezara.ikode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.sinch.verification.VerificationListener;
import com.sinch.verification.Verification;
import com.sinch.verification.InvalidInputException;
import com.sinch.verification.ServiceErrorException;
import com.sinch.verification.CodeInterceptionException;
import com.sinch.verification.IncorrectCodeException;

/**
 * Created by viezara on 29/10/2015.
 */
public class sample extends AppCompatActivity implements View.OnClickListener{

    TextView verificationCounter;
    EditText edt;
    Button btn_send;

    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.sample_layout);
        Intent i = getIntent();
        Bundle b2 = i.getExtras();

        verificationCounter = (TextView) findViewById(R.id.verificationCounter);
        /*
        Config config = SinchVerification.config().applicationKey("180ed755-d854-4ac3-a778-3fd06dc3098e").context(getApplicationContext()).build();
        VerificationListener listener = new MyVerificationListener();
        Verification verification = SinchVerification.createFlashCallVerification(config, "+64221876594", listener);
        verification.initiate();
        */

        Toast.makeText(sample.this, b2.getString("bcode"), Toast.LENGTH_LONG);
        edt = (EditText) findViewById(R.id.barcode);
        edt.setText((String) b2.getString("bcode"));

        btn_send = (Button) findViewById(R.id.send);
        btn_send.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if(v == btn_send){
            //Code Insert
            Intent intent = new Intent(this, VerifyData.class);
            String barcode_ID = edt.getText().toString();
            intent.putExtra(RequestData.barcode_ID,barcode_ID);
            startActivity(intent);
            //End Insert
        }
    }


    class MyVerificationListener implements VerificationListener {

        @Override
        public void onInitiated() {
            // Verification initiated
            verificationCounter.setText("Verification initiated");
        }
        @Override
        public void onInitiationFailed(Exception e) {
            if (e instanceof InvalidInputException) {
                // Incorrect number provided
                verificationCounter.setText("Incorrect number Provided");
            } else if (e instanceof ServiceErrorException) {
                // Sinch service error
                verificationCounter.setText("Service error");
                Log.e(Verification.class.getSimpleName(), "THIS>>>>>>>>" + e.getMessage());
            } else {
                // Other system error, such as UnknownHostException in case of network error
                verificationCounter.setText("Other error");
            }
        }
        @Override
        public void onVerified() {
            // Verification successful
            verificationCounter.setText("Verified");
        }
        @Override
        public void onVerificationFailed(Exception e) {
            if (e instanceof InvalidInputException) {
                // Incorrect number or code provided
                verificationCounter.setText("Incorrect number or code provided");
            } else if (e instanceof CodeInterceptionException) {
                // Intercepting the verification code automatically failed, input the code manually with verify()
                verificationCounter.setText("Verification Failed. Please use your phone with your number.");
            } else if (e instanceof IncorrectCodeException) {
                // The verification code provided was incorrect
                verificationCounter.setText("The verification code provided was incorrect");
            } else if (e instanceof ServiceErrorException) {
                // Sinch service error
                verificationCounter.setText("");
            } else {
                // Other system error, such as UnknownHostException in case of network error
                verificationCounter.setText("Other system error 2");
            }
        }

    }
}
