package com.thuyvy.storm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.thuyvy.storm.adapter.StormAdapter;
import com.thuyvy.storm.dbhelper.StormDbHelper;
import com.thuyvy.storm.model.Storm;

import java.util.ArrayList;
import java.util.List;

public class ShowStormSQLiteActivity extends AppCompatActivity {
    private ListView listView;
    private List<Storm> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_storm_sqlite);

        listView = findViewById(R.id.listView);
        data = new ArrayList<>();

        loadDataFromFile();

        StormAdapter adapter = new StormAdapter(this, data);
        listView.setAdapter(adapter);
    }

    private void loadDataFromFile() {
        StormDbHelper helper = new StormDbHelper(this);
        data = helper.getAllEntries();
    }
}
