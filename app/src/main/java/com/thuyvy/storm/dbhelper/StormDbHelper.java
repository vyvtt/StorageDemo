package com.thuyvy.storm.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thuyvy.storm.contracts.StormContracts;
import com.thuyvy.storm.model.Storm;

import java.util.ArrayList;
import java.util.List;

public class StormDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    public StormDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StormContracts.StormEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StormContracts.StormEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addEntry(Storm storm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StormContracts.StormEntry.COLUMN_NAME_ID, storm.getId());
        values.put(StormContracts.StormEntry.COLUMN_NAME_NAME, storm.getName());
        values.put(StormContracts.StormEntry.COLUMN_NAME_WINDSPEED, storm.getWindSpeed());
        values.put(StormContracts.StormEntry.COLUMN_NAME_DES, storm.getDescription());

        db.insert(StormContracts.StormEntry.TABLE_NAME, null, values);

        db.close();
    }

    public List<Storm> getAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Storm> entries = new ArrayList<>();

        Cursor cursor = db.query(StormContracts.StormEntry.TABLE_NAME,
                new String[] {
                        StormContracts.StormEntry._ID,
                        StormContracts.StormEntry.COLUMN_NAME_ID,
                        StormContracts.StormEntry.COLUMN_NAME_NAME,
                        StormContracts.StormEntry.COLUMN_NAME_WINDSPEED,
                        StormContracts.StormEntry.COLUMN_NAME_DES
                },
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
//                int id = cursor.getInt(0);
                String id = cursor.getString(1);
                String name = cursor.getString(2);
                float windSpeed = cursor.getFloat(3);
                String des = cursor.getString(4);
                entries.add(new Storm(id, name, windSpeed, des));
            }
            cursor.close();
        }
        db.close();
        return entries;
    }
}
