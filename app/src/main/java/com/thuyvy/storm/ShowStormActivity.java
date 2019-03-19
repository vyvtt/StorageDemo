package com.thuyvy.storm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.thuyvy.storm.adapter.StormAdapter;
import com.thuyvy.storm.model.Storm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowStormActivity extends AppCompatActivity {
    private ListView listView;
    private List<Storm> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_storm);

        listView = findViewById(R.id.listView);
        data = new ArrayList<>();

        loadDataFromFile();

        StormAdapter adapter = new StormAdapter(this, data);
        listView.setAdapter(adapter);
    }


    private void loadDataFromFile() {
        Gson gson = new Gson();

        try {
            FileInputStream fis = openFileInput("Storm.dat");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
//                Storm storm = gson.fromJson(line, Storm.class);
//                data.add(storm);

                JSONObject jsonStorm = new JSONObject(line);
                Storm stormFromJSON = new Storm(
                        jsonStorm.getString("id"),
                        jsonStorm.getString("name"),
                        (float) jsonStorm.getDouble("windSpeed"),
                        jsonStorm.getString("windSpeed")
                );
                data.add(stormFromJSON);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
