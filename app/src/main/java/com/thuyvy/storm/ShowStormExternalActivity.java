package com.thuyvy.storm;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.thuyvy.storm.adapter.StormAdapter;
import com.thuyvy.storm.model.Storm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowStormExternalActivity extends AppCompatActivity {
    private ListView listView;
    private List<Storm> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_storm_external);

        listView = findViewById(R.id.listView);
        data = new ArrayList<>();

        loadDataFromFile();

        StormAdapter adapter = new StormAdapter(this, data);
        listView.setAdapter(adapter);
    }

    private void loadDataFromFile() {
        Gson gson = new Gson();

        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = sdCard.getAbsolutePath();

        File directory = new File(realPath + "/StormFile");
        File file = new File(directory, "Storm.dat");

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                Storm storm = gson.fromJson(line, Storm.class);
                data.add(storm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
