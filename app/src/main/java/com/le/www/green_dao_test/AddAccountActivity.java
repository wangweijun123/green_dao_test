package com.le.www.green_dao_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.greendao.example.Account;
import org.greenrobot.greendao.example.App;
import org.greenrobot.greendao.example.DaoSession;

/**
 * Created by wangweijun1 on 2017/6/17.
 */

public class AddAccountActivity extends AppCompatActivity {

    private EditText platform_name;
    private EditText official_web;
    private EditText username;
    private EditText login_pwd;
    private EditText pay_pwd;

    private Button bt_addAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account);
        setTitle(R.string.account_add);
        initViews();
    }

    private void initViews() {
        platform_name = (EditText) findViewById(R.id.platform_name);
        official_web = (EditText) findViewById(R.id.official_web);
        username = (EditText) findViewById(R.id.username);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        pay_pwd = (EditText) findViewById(R.id.pay_pwd);

        bt_addAccount = (Button)findViewById(R.id.bt_addAccount);
    }

    long id;
    public void addAccount(View v) {
        Account account = new Account();
        account.setPlatformName(platform_name.getText().toString());
        account.setOfficialWeb(official_web.getText().toString());
        account.setUserName(username.getText().toString());
        String s = login_pwd.getText().toString();
        if (s == null) {
            Log.d("DaoExample", "s == null" );
        }

        if (s.equals("")) {
            Log.d("DaoExample", "s.equals(\"\")" );
        }
        account.setLoginPassword(s);//  account.setLoginPassword(null);
        account.setPayPassword(pay_pwd.getText().toString());

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        id = daoSession.getAccountDao().insert(account);
        Log.d("DaoExample", id + " Inserted new account, ID: " + account.getId());
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
