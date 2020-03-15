package com.engineering.dokkan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);

        ArrayList<String> list = AddingOptionsToList();

        OptionAdapter adapter = new OptionAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            }
        });

    }
    public ArrayList<String> AddingOptionsToList (){
        final ArrayList<String> list = new ArrayList<String>();
        list.add("My Orders");
        list.add("Messages");
        list.add("Reviews");
        list.add("Help");
        list.add("Languages");
        list.add("My Address Book");
        return list ;
    }

}
