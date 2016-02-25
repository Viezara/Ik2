package com.ikode.viezara.ikode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserConnect extends Activity {

    private EditText USERNAME, PASSWORD;
    private Button CONNECT;
    private String input_password="";
    private String input_email="";
    //private TextView cancel;
    private SharedPreferences SP;
    private Button FORGOTPASSWORD;


    //code from Phone Verification.Java
    private EditText txtSecurityCode;
    private String Phrase;
    private String PhoneNumber,SecurityCode;
    private String convertPhrase="";
    private StringBuilder sb;
    private TextView codeHandler;
    private String Security_Code="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_connect);

        //original code from Aries and Demy
//        USERNAME = (EditText) findViewById(R.id.username);
//        PASSWORD = (EditText) findViewById(R.id.password);
        CONNECT = (Button) findViewById(R.id.connectBtn);
//        cancel = (TextView) findViewById(R.id.textView5);
        FORGOTPASSWORD = (Button) findViewById(R.id.forgotToken);



        //for passphrase change code
        txtSecurityCode = (EditText) findViewById(R.id.password);
        Intent intent = getIntent();
        PhoneNumber = intent.getStringExtra("serial");
        Phrase = intent.getStringExtra("phrase");
        Security_Code = intent.getStringExtra(RequestData.display_code);
        codeHandler = (TextView) findViewById(R.id.username);
        //codeHandler.setText(Phrase);
        convertPhrase=Phrase.toString();
        int str_len = (Phrase.toString().length()/2);
        sb = new StringBuilder(Phrase.toString());
        for (int index = str_len; index < sb.length(); index++) {
            if (sb.charAt(index) == ' ') {
            } else {
                sb.setCharAt(index, '*');
            }
        }
        codeHandler.setText(sb.toString());


        CONNECT.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                SecurityCode = txtSecurityCode.getText().toString();

                if (isEmpty(SecurityCode) == false) {
                    SharedPreferences.Editor editor = SP.edit();
                    editor.putString(RequestData.SESSION_CODE, USERNAME.getText().toString());
                    editor.commit();

                    //getData();
                    verify();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter security code before you can Login", Toast.LENGTH_LONG).show();
                }
            }

        });



        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);

        if(!RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){


        } else {
            Intent TO_USERCONNECT = new Intent(this, UserProfile.class);
            startActivity(TO_USERCONNECT);
        }
    }

    private void verify(){
        if ( (SecurityCode.equals(convertPhrase))) {Toast.makeText(getApplicationContext(), "User Credential is Verified!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent("android.intent.action.UserProfile");
            startActivity(intent);
            onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "Verification failed: Code is Incorrect or Expired ", Toast.LENGTH_LONG).show();
        }
    }

           /* cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(UserConnect.this, homescreen.class);
                    startActivity(intent);
                }
            });*/

           /* CONNECT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    input_password = PASSWORD.getText().toString().trim();
                    input_email = USERNAME.getText().toString().trim();
                    if (isValid(input_email)) {
                        if (!isEmpty(input_password)) {

                            SharedPreferences.Editor editor = SP.edit();
                            editor.putString(RequestData.SESSION_CODE, USERNAME.getText().toString());
                            editor.commit();

                            connectUser();

                        } else {
                            Toast.makeText(getApplicationContext(), "Password is empty", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                        USERNAME.requestFocus();
                    }

                }
            });*/


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


   /* @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // initiating passphrase ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }*/

    //Onclick TextView
    public void onClick(View v) {

    }

    //check password
    private boolean isEmpty(String pword) {
        if (pword.toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    //check valid email
    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }

    //Adding an connectUser
    private void connectUser(){

        class conUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserConnect.this,"Connecting...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUser(s);
                //Toast.makeText(UserConnect.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(RequestData.KEY_Email,input_email);
                params.put(RequestData.KEY_Pass,input_password);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_GET_USER, params);
                return s;
            }
        }

        conUser ae = new conUser();
        ae.execute();
    }
    private void showUser(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(RequestData.TAG_ERROR);
            String msg = jsonObject.getString(RequestData.TAG_Message);
            if(error.equals("false"))
            {
                Toast.makeText(UserConnect.this, msg, Toast.LENGTH_LONG).show();
                Intent userChoice = new Intent("android.intent.action.UserProfile");//changed this to User Profile
                startActivity(userChoice);
                onBackPressed();
            }
            else
            {
                Toast.makeText(UserConnect.this, msg, Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
