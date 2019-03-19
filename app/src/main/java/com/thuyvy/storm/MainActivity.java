package com.thuyvy.storm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickTtoAddNew(View view) {
        Intent intent = new Intent(this, AddStormActivity.class);
        startActivity(intent);
    }

    public void clickToShow(View view) {
        Intent intent = new Intent(this, ShowStormActivity.class);
        startActivity(intent);
    }

    public void clickToShowExternal(View view) {
        Intent intent = new Intent(this, ShowStormExternalActivity.class);
        startActivity(intent);
    }

    public void clickToShowSharePreferences(View view) {
        Intent intent = new Intent(this, ShowStormSharePreferencesActivity.class);
        startActivity(intent);
    }
}
