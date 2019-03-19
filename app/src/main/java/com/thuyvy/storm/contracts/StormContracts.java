package com.thuyvy.storm.contracts;

import android.provider.BaseColumns;

public class StormContracts {

    private StormContracts() {}

    /* Inner class that defines the table contents */
    public static class StormEntry implements BaseColumns {
        public static final String TABLE_NAME = "storm";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WINDSPEED = "windSpeed";
        public static final String COLUMN_NAME_DES = "description";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + StormEntry.TABLE_NAME + " (" +
                        StormEntry._ID + " INTEGER PRIMARY KEY," +
                        StormEntry.COLUMN_NAME_ID + " TEXT," +
                        StormEntry.COLUMN_NAME_NAME + " TEXT," +
                        StormEntry.COLUMN_NAME_WINDSPEED + " FLOAT," +
                        StormEntry.COLUMN_NAME_DES + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + StormEntry.TABLE_NAME;
    }
}
