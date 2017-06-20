package com.le.www.green_dao_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangweijun1 on 2017/6/20.
 */

public class AppUpdateIgnoreHelper extends SQLiteOpenHelper {
    private static AppUpdateIgnoreHelper dbOpenHelper = null;
    public static AppUpdateIgnoreHelper getInstance(Context context) {
        if (dbOpenHelper == null) {
            synchronized (Object.class) {
                if (null == dbOpenHelper) {
                    dbOpenHelper = new AppUpdateIgnoreHelper(context);
                }
                return dbOpenHelper;
            }
        } else {
            return dbOpenHelper;
        }
    }

    private AppUpdateIgnoreHelper(Context context) {
        super(context, "notes-db", null, 25);
    }

    public AppUpdateIgnoreHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
