package com.ikode.viezara.ikode;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


public class HelpPage extends AppCompatActivity {

    ListView listView;
    SearchView searchBox;


    String[] titles={
            "What is the purpose of the Ikode SC Decoder?",
            "How does the Ikode SC  Decoder work?",
            "What is an Ikode Smartcode code for?",
            "Does the Ikode SC Decoder access files and folders on my mobile device?",
            "Why doesn’t the Ikode SC Decoder work on my phone?",
            "Does Ikode SC Decoder send messages to my contacts?",
            "How do I change the email associated with the account?	",
            "Why are my email address and password are not working?",
            "What should I do if I don't receive my scan activation?",
            "What should I do if I don't receive a scan verification result?",
            "What should I do if I don't receive my activation email? ",
            "How do I create a code?",
            "(if user registers we should issue a temp(?) registration No. displayed in a separate  txtbox at the bottom of the registration screen. If no activation email received user can resend reg. No., only)",
            "Can Ikode smartcodes be read by non-Ikode SC Decoder mobile readers?"
    };

    String[] contents = {
            "The Ikode SC Decoder is a truly unique barcode / smartcode decoder.On one hand it is the Swiss Army Knife in decoding almost all available types of barcodes, such as: One line (1D barcodes): UPC-A and UPC-E,EAN-8 and EAN-13, Code 39, Code 93, Code 128, ITF, Codabar (2-dimensional (2D) barcodes): QR Code, Data Matrix, PDF 417 ('alpha' quality), Aztec ('beta' quality), (Read more about the types of barcodes …) On the other hand the Ikode SC Decoder, installed on your smart mobile phone is a tool that allows you to verify the authenticity of products, documents and services of participating brand and corporations in the fight to bust fraud.",
            "Launch the Ikode SC Decoder on your device. It will require an authentication (mobile number). Within seconds the camera will become active and is ready for scanning. Line up the phone so that the Code is completely inside the viewfinder marks of your camera. If lighting is a problem and your camera can’t focus on the code, please re-position the phone. After scanning the decoded Code data is immediately visible to you and prompts you to do the authenticity verification.",
            "None",
            "None",
            "We primarily support Android phones and, soon to come, iPhones and Windows phones. Check http://www.ikodesoftware.com/decoder for a list of models and operating systems that we support. Phones can be tricky when it comes to their operating systems (OS). Please note that we do not support Symbian phones.",
            "No, absolutely not. Our Privacy Policy is very clear on this. We do not access any files or folders on your smartphone that is not part of the Ikode SC Decoder app.",
            "(Registration / Admin)",
            "(Registration / Admin)",
            "(Accessing Ikode SC Decoder)",
            "(issue linked to company generating Ikodes)",
            "(Registration)",
            "(expand Ikode SC Decoder app to generate Ikode vCard codes from mobile phone's Contacts or from a form field (sep screen) resembling Ikode vCard solution).",
            "Official A: “Don't worry, we can help you! First check your SPAM folder, depending on your email settings the activation email may end up there. If that is not the case, please resend Reg. No.“ (?)",
            "Official A: “QR Codes can be read by any QR scanner app. Data Matrix codes can be read by any Data Matrix scanner app. There are a growing number of mobile readers that can scan both codes. However, these 3rd-party mobile readers not necessarily have the added functionality to request and receive an authenticity verification in your quest of fighting fraud.”"
    };

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        listView = (ListView)findViewById(R.id.list);
        searchBox = (SearchView) findViewById(R.id.searchbox);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);

        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }

            public boolean onQueryTextChange(String query) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(query);
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String chosenTitle = ((TextView) view).getText().toString();

                String titleValue = getValue(chosenTitle);

                TextView tx = new TextView(HelpPage.this);
                tx.setText(titleValue);
                tx.setGravity(Gravity.TOP);

                new AlertDialog.Builder(HelpPage.this, R.style.Theme_Ikode_Dialog_Alert).setTitle("MESSAGE").setMessage("CONTENT: ").setView(tx).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.ikode_bar);
       // setSupportActionBar(toolbar);

        //test for returning to previous activity
       // getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        try {
            parseData();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        */


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.Layout.simple_List_item_1, searches );
        /*SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(context, HelpPage.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));*/

    }
    private String getValue(String title) {

        String content = "";
        for(int i = 0; i < titles.length; i++) {
            if(titles[i].equals(title)) {
                content = contents[i];
            }
        }

        return content;
    }
/*
    private void parseData () throws Exception {
        InputSource source = new InputSource(getResources().openRawResource(R.raw.searchable_items));
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//name/title['What is the purpose of the Ikode SC Decoder?']";
        NodeList nodes = (NodeList) xpath.evaluate(expression, source, XPathConstants.NODESET);
        Toast.makeText(getApplicationContext(), "COUNT: " + String.valueOf(nodes.getLength()), Toast.LENGTH_LONG).show();

        String keyword = "What is the purpose of the Ikode SC Decoder?";

        if (nodes != null && nodes.getLength() > 0) {
            searches.clear();
            int len = nodes.getLength();

            for (int i = 0; i < len; ++i) {
                Node node = nodes.item(i);
                searches.add(node.getTextContent());
                Toast.makeText(getApplicationContext(), "COUNT: " + node.getTextContent(), Toast.LENGTH_LONG).show();
            }
        }
    }
    */
    //to go back to same task *gmbg* not sure on what menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //to go back to same task *gmbg*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


}
