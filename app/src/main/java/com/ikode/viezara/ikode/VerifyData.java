package com.ikode.viezara.ikode;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class VerifyData extends AppCompatActivity {

    private EditText editDataId;
    private EditText editDataType;
    private EditText editDesc;
    private EditText editVer;
    private ImageView imageView;
    private Button buttonSets, buttonSave;
    private SharedPreferences SP;

    //for Toolbar
    private Toolbar toolbar;

    private String id;
    private String docId;
    private String token;

    private String JSON_STRING;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_data);

        //for toolbar
        Toolbar  actionBarToolBar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(actionBarToolBar);
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_camera);
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_person);


        Intent intent = getIntent();

        id = intent.getStringExtra(RequestData.barcode_ID);

        Toast.makeText(VerifyData.this, id, Toast.LENGTH_LONG);

        editDataId = (EditText) findViewById(R.id.editDataId);
        editDataType = (EditText) findViewById(R.id.editDataType);
        editDesc = (EditText) findViewById(R.id.editDesc);
        editVer = (EditText) findViewById(R.id.editVer);
        imageView  = (ImageView) findViewById(R.id.imageView);
        buttonSets = (Button) findViewById(R.id.settButt);
        buttonSave = (Button) findViewById(R.id.saveButt);

        editDataId.setText(id);

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);
        String uname = SP.getString(RequestData.SESSION_CODE, RequestData.DEFAULT_SESSION_VALUE);

        if(uname.equals(RequestData.DEFAULT_SESSION_VALUE)){
            buttonSets.setVisibility(View.GONE);
            buttonSave.setVisibility(View.GONE);
        }

        getData();

        buttonSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TO_SETTINGS = new Intent("android.intent.action.Settings1");
                startActivity(TO_SETTINGS);
            }



        });
    }

    private void getData(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VerifyData.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showData(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(RequestData.URL_GET_DATA,id);

                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);

            //int success = jsonObject.getInt(Config.TAG_SUCCESS);
            //if(success==1) {
                JSONArray result = jsonObject.getJSONArray(RequestData.TAG_JSON_ARRAY);
                JSONObject c = result.getJSONObject(0);
                String type = c.getString(RequestData.TAG_TYPE);
                String desc = c.getString(RequestData.TAG_DESC);
                String ver = c.getString(RequestData.TAG_VER);

                    editDataId.setText(id);
                    editDataType.setText(type);
                    editDesc.setText(desc);
                    editVer.setText(ver);
                    getImage();

            //}
            //else {
            //    Toast.makeText(VerifyData.this, "Data not found!", Toast.LENGTH_LONG).show();
            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Start Insert
    private void getImage() {
        docId = id;
        class GetImage extends AsyncTask<String,Void,Bitmap>{


            ImageView bmImage;
            ProgressDialog loading1;

            public GetImage(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                loading1.dismiss();
                bmImage.setImageBitmap(bitmap);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading1 = ProgressDialog.show(VerifyData.this,"Downloading Image","Please wait...",true,true);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String url = RequestData.URL_GET_DATA_IMAGE + strings[0];
                Bitmap mIcon = null;
                try {
                    InputStream in = new java.net.URL(url).openStream();
                    mIcon = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                return mIcon;
            }
        }

        GetImage gi = new GetImage(imageView);

        gi.execute(docId);
    }

}
