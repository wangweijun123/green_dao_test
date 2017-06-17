package com.le.www.green_dao_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.greendao.example.Account;
import org.greenrobot.greendao.example.AccountDao;
import org.greenrobot.greendao.example.App;
import org.greenrobot.greendao.example.DaoSession;
import org.greenrobot.greendao.query.Query;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AccountDao accountDao;
    private Query<Account> accountsQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        accountDao = daoSession.getAccountDao();
    }

    public void getAccountList(View v) {
        accountsQuery = accountDao.queryBuilder().build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }

    }


    public void addAccount(View v) {
        Account account = new Account();
        account.setUserName("wangweijun");
        account.setLoginPassword("123456");
        account.setPayPassword("pppp");
        accountDao.insert(account);
        Log.d("DaoExample", "Inserted new account, ID: " + account.getId());
    }
}
