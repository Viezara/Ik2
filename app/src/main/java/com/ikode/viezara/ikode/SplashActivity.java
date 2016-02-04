package com.ikode.viezara.ikode;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity  {

    private static final int PROGRESS = 0X1;
    private int iProgressStatus = 0;
    private Handler iHandler = new Handler();
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public static final int seconds=10;
    public static final int miliseconds=seconds*1000;
    private ProgressBar iProgress;

//    private Thread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.content_splash);

        iProgress = (ProgressBar) findViewById(R.id.progressBar);
        iProgress.setMax(seconds - 1);
        cd = new ConnectionDetector(getApplicationContext());

        
        iProgress.setOnClickListener(new View.OnClickListener() {

            TextView text = new TextView(SplashActivity.this);



            @Override
            public void onClick(View v) {
                isInternetPresent = cd.isConnnectedToNet();
                if (isInternetPresent) {
                    Intent intent = new Intent("android.intent.action.Settings1");
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(SplashActivity.this).setTitle("MESSAGE").setMessage("No Connection").setView(text).setNegativeButton("CLOSE",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }
            }
        });


        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (iProgressStatus < 100){
                    iProgressStatus += 1;

                    iHandler.post (new Runnable(){
                        public void run() {
                            iProgress.setProgress(iProgressStatus);
                        }
                    });
                    try{
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

/*        timerThread = new Thread() {
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, homescreen.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }
            }
        };*/
//        timerThread.start();
        startAnimation();

    }


    public void startAnimation(){
        new CountDownTimer(miliseconds,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //progressbar update--->1 second
                iProgress.setProgress(getMiliseconds(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(SplashActivity.this, homescreen.class);
                SplashActivity.this.startActivityForResult(i,1);
            }
        }.start();
    }

    private int getMiliseconds(long milis) {

        return (int) ((milis)/1000);
    }


/*
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (timerThread) {
                timerThread.notifyAll();
            }
        }

        return true;
    }


    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
*/

   /* private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager=(ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo )
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }*/

}





        /*setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/