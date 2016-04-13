package com.ikode.viezara.ikode;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;



public class SplashActivity extends AppCompatActivity implements OnProgressBarListener {

    private static final int PROGRESS = 0X1;
    private int iProgressStatus = 0;
    private Handler iHandler = new Handler();
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public static final int seconds = 10;
    public static final int miliseconds = seconds * 1000;
    private ProgressBar iProgress;
    TextView text;
    public boolean no_Connection = true;
    LoginDataBaseAdapter loginDataBaseAdapter;

    private Timer timer;
    private NumberProgressBar iProgress1;

    private int jumpTime = 0;
    private static final String TAG = "myApp";

    private TextView iklsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //for ikona License spannable

        iklsplash = (TextView) findViewById(R.id.iklsplashTxt);
        SpannableString iklsplashtxt = new SpannableString("licensed by Ikona®");

        iklsplashtxt.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        iklsplashtxt.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        iklsplashtxt.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        iklsplash.setMovementMethod(LinkMovementMethod.getInstance());
        iklsplash.setText(iklsplashtxt);
        iklsplash.setHighlightColor(Color.TRANSPARENT);


        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        iProgress1 = (NumberProgressBar) findViewById(R.id.numberbar1);
        iProgress1.setOnProgressBarListener(this);

        RequestData.storedEmail = loginDataBaseAdapter.getUserEmail();
        cd = new ConnectionDetector(getApplicationContext());
        Connection();
        //display();
    }

    public void Connection() {
        isInternetPresent = cd.isConnnectedToNet();
        if (isInternetPresent) {
            no_Connection = false;
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        iProgress1.incrementProgressBy(2);
                        jumpTime += 2;
                        //Log.v(TAG, "jumpTime "+ jumpTime);
                        if (jumpTime == 100) {
                            display();
                            onFinish();
                        }
                    }
                }
                );
            }
                public void onFinish()
                {
                    timer.cancel();
                }

        }, 100, 100);

        } else {
            new AlertDialog.Builder(SplashActivity.this).setTitle("MESSAGE").setMessage("No Internet Connection, Please check your connection settings!").setView(text).setNegativeButton("CLOSE",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            no_Connection = true;
                            onBackPressed();
                        }
                    }).show();

        }
        //display();

    }


    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void onProgressChange(int current, int max) {

        if(current == max) {

        }

    }



    public void display()
    {

            //RequestData.storedEmail = loginDataBaseAdapter.getUserEmail();
            if (RequestData.storedEmail.isEmpty()==false) {
                connectUser();
            } else {
                RequestData.user_Registered = "false";
                Intent i = new Intent(SplashActivity.this, homescreen.class);
                SplashActivity.this.startActivityForResult(i, 1);
                onBackPressed();
            }
    }
    // code inserted start
    private void connectUser(){
        class conUser extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(SplashActivity.this,"Connecting...","Wait...",true,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showUser(s);
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(RequestData.KEY_Email,RequestData.storedEmail);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_CONNECTION, params);
                return s;
            }
        }
        conUser ae = new conUser();
        ae.execute();
    }
    private void showUser(String json){
        //Toast.makeText(SplashActivity.this, "insert : " + json.toString(), Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(RequestData.TAG_ERROR);
            String msg = jsonObject.getString(RequestData.TAG_Message);
            if(error.equals("false"))
            {
                RequestData.user_Registered = "true";
                RequestData.getToken = jsonObject.getString(RequestData.TAG_Token);
                //Log.v(TAG, "jsonObject: "+ msg +" "+RequestData.getToken);
                Intent i = new Intent(SplashActivity.this, homescreen.class);
                SplashActivity.this.startActivityForResult(i, 1);
                onBackPressed();
            }
            else
            {
                RequestData.user_Registered = "false";
                Intent i = new Intent(SplashActivity.this, homescreen.class);
                SplashActivity.this.startActivityForResult(i, 1);
                onBackPressed();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            RequestData.user_Registered = "false";
            //Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}


