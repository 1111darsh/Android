package com.memighty.loginsignupusingsqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context){
        super(context,"Database.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table person (User TEXT,Email TEXT,Password TEXT,Number TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists person");
        onCreate(db);
    }
}
