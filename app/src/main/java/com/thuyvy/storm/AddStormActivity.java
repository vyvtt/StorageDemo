package com.thuyvy.storm;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thuyvy.storm.dbhelper.StormDbHelper;
import com.thuyvy.storm.model.Storm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class AddStormActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtSpeed, edtDes;
    private Storm storm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_storm);

        edtDes = findViewById(R.id.edt_des);
        edtName = findViewById(R.id.edt_name);
        edtSpeed = findViewById(R.id.edt_windspeed);
        edtId = findViewById(R.id.edt_id);
    }

    public void clickToSaveInternal(View view) {
        if (getData()) {
            writeInternalFile();
//            finish();
        }
    }

    private boolean getData() {
        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String des = edtDes.getText().toString().trim();
        String strSpeed = edtSpeed.getText().toString();
        float speed = 0;

        if (strSpeed.isEmpty()) {
            Toast.makeText(this, "Empty field!", Toast.LENGTH_LONG).show();
            return false;
        }
        speed = Float.valueOf(strSpeed);

        if (id.isEmpty() || name.isEmpty() || des.isEmpty()) {
            Toast.makeText(this, "Empty field!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (speed <= 1) {
            Toast.makeText(this, "Wind Speed must > 1", Toast.LENGTH_LONG).show();
            return false;
        }

        storm = new Storm(id, name, speed, des);
        return true;
    }

    private void writeInternalFile() {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            fos = openFileOutput("Storm.dat", MODE_PRIVATE | MODE_APPEND);
            osw = new OutputStreamWriter(fos);

//            Gson gson = new Gson();
//            String tmp = gson.toJson(storm);

            JSONObject jsonStorm = new JSONObject();
            try {
                jsonStorm.put("id", storm.getId());
                jsonStorm.put("name", storm.getName());
                jsonStorm.put("windSpeed", storm.getWindSpeed());
                jsonStorm.put("description", storm.getDescription());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            osw.write(jsonStorm.toString() + "\n");

//            osw.write(tmp + "\n");
            osw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
//            finish();
        }
    }

    public void clickToSaveExternal(View view) {
        if (getData()) {
            writeExternalFile();
//            finish();
        }
    }

    private void writeExternalFile() {
        File sdCard = Environment.getExternalStorageDirectory();
        String realPath = sdCard.getAbsolutePath();

        File directory = new File(realPath + "/StormFile");
        directory.mkdir();

        File file = new File(directory, "Storm.dat");
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);

            Gson gson = new Gson();
            String tmp = gson.toJson(storm);
            osw.write(tmp + "\n");
            osw.flush();

            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clickToSavePreference(View view) {
        if (getData()) {
            writeSharePreference();
//            finish();
        }
    }

    private void writeSharePreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.thuyvy.storm.database_preferences", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);

        Gson gson = new Gson();
        String tmp = gson.toJson(storm);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("storm" + count, tmp);
        count++;
        editor.putInt("count", count);
        editor.commit();

        Toast.makeText(this, "Save preference success", Toast.LENGTH_SHORT).show();
    }

    public void clickToSaveSQLite(View view) {
        if (getData()) {
            StormDbHelper helper = new StormDbHelper(this);
            helper.addEntry(storm);
            Toast.makeText(this, "Save SQLite success", Toast.LENGTH_SHORT).show();
        }
    }
}
