package org.greenrobot.greendao.example;

import android.app.Application;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.example.DaoMaster.DevOpenHelper;

public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

//        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
//        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
//        daoSession = new DaoMaster(db).newSession();


        MigrationHelper.DEBUG = true;
        MySQLiteOpenHelper helper2 = new MySQLiteOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db",
                null);
        Database db = ENCRYPTED ? helper2.getEncryptedWritableDb("super-secret") : helper2.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
