package com.ikode.viezara.ikode;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity  {

    private static final int PROGRESS = 0X1;
    private int iProgressStatus = 0;
    private Handler iHandler = new Handler();
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public static final int seconds=10;
    public static final int miliseconds = seconds*1000;
    private ProgressBar iProgress;
    TextView text;
    public boolean no_Connection=true;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.content_splash);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        iProgress = (ProgressBar) findViewById(R.id.progressBar);
        iProgress.setMax(seconds - 1);

       // iProgress.setRotation(360);

        cd = new ConnectionDetector(getApplicationContext());
        //connectUser();
        RequestData.storedEmail =loginDataBaseAdapter.getUserEmail();
        Connection();
    }

    public void Connection()
    {
        isInternetPresent = cd.isConnnectedToNet();
        if (isInternetPresent) {
                new CountDownTimer(miliseconds,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //progressbar update--->1 second
                        iProgress.setProgress(getMiliseconds(millisUntilFinished));

                    }

                    @Override
                    public void onFinish() {
                        connectUser();
                    }
                }.start();
            no_Connection=true;
        } else {
            new AlertDialog.Builder(SplashActivity.this).setTitle("MESSAGE").setMessage("No Internet Connection, Please check your connection settings!").setView(text).setNegativeButton("CLOSE",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            no_Connection=true;
                            onBackPressed();
                        }
                    }).show();

        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    private int getMiliseconds(long milis) {

        return (int) (1000/(milis));
    }



    public void display()
    {
        RequestData.storedEmail =loginDataBaseAdapter.getUserEmail();
        connectUser();

    }
    //// code inserted start
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
            //Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}


