package com.android.smartportao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaime on 05/07/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public  static final String TABLE_NAME_SETTINGS_DEVICE = "settingsDevice";
    public  static final String TABLE_NAME_RANK_RUN = "settingsD";


    //optional
    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
