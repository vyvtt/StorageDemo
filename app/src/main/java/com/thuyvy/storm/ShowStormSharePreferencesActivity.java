package com.thuyvy.storm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.thuyvy.storm.adapter.StormAdapter;
import com.thuyvy.storm.model.Storm;

import java.util.ArrayList;
import java.util.List;

public class ShowStormSharePreferencesActivity extends AppCompatActivity {
    private ListView listView;
    private List<Storm> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_storm_share_preferences);

        listView = findViewById(R.id.listView);
        data = new ArrayList<>();

        loadDataFromFile();

        StormAdapter adapter = new StormAdapter(this, data);
        listView.setAdapter(adapter);
    }

    private void loadDataFromFile() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.thuyvy.storm.database_preferences", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);

        Gson gson = new Gson();

        System.out.println("count: " + count);

        for (int i = 0; i < count; i++) {
            String tmp = sharedPreferences.getString("storm" + i, "");
            System.out.println("tmp: " + tmp);
            Storm storm = gson.fromJson(tmp, Storm.class);
            System.out.println(storm.toString());
            data.add(storm);
        }
    }
}
