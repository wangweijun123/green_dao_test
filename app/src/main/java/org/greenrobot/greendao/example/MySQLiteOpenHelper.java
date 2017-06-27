package org.greenrobot.greendao.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.test.entityannotation.CustomerDao;
import org.greenrobot.greendao.test.entityannotation.OrderDao;

/**
 * Created by wangweijun1 on 2017/6/17.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, AccountDao.class, NoteDao.class, PictureDao.class, UserDao.class,
                OrderDao.class, CustomerDao.class);
    }
}
