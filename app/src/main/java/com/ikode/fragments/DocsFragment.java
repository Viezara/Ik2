package com.ikode.fragments;

import android.app.ListFragment;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ikode.viezara.ikode.LoginDataBaseAdapter;
import com.ikode.viezara.ikode.R;

import org.w3c.dom.Text;

/**
 * Created by jem on 30/01/16.
 */
public class DocsFragment extends Fragment {
    LoginDataBaseAdapter loginDataBaseAdapter;
    private SimpleCursorAdapter dataAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //displayListView();
        View v =inflater.inflate(R.layout.docu, container,false);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        Cursor cursor = loginDataBaseAdapter.fetchAllDocs();

        // The desired columns to be bound
        String[] columns = new String[]{
                "_id",
                "type",
                "desc"
        };
        // the XML defined views which the data will be bound tso
        int[] to = new int[] {
                R.id.list_ID,
                R.id.list_type,
                R.id.list_desc,
        };

        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_row, cursor, columns, to, 0);
        ListView myList = (ListView) v.findViewById(R.id.listView1);
        myList.setAdapter(dataAdapter);

        return v;


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}
