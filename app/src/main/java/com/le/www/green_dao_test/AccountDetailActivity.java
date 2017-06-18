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

public class AccountDetailActivity extends AppCompatActivity {

    private EditText platform_name;
    private EditText official_web;
    private EditText username;
    private EditText login_pwd;
    private EditText pay_pwd;

    private Button bt_addAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_account);
        setTitle(R.string.account_detail);
        initViews();
    }
    Account account;
    private void initViews() {
        platform_name = (EditText) findViewById(R.id.platform_name);
        official_web = (EditText) findViewById(R.id.official_web);
        username = (EditText) findViewById(R.id.username);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        pay_pwd = (EditText) findViewById(R.id.pay_pwd);

        bt_addAccount = (Button)findViewById(R.id.bt_addAccount);

        account = (Account)(getIntent().getSerializableExtra("account"));
        platform_name.setText(account.getPlatformName());
        official_web.setText(account.getOfficialWeb());
        username.setText(account.getUserName());
        login_pwd.setText(account.getLoginPassword());
        pay_pwd.setText(account.getPayPassword());
    }

    public void updateAccount(View v) {
        Account account = new Account();
        account.setId(this.account.getId());
        account.setPlatformName(platform_name.getText().toString());
        account.setOfficialWeb(official_web.getText().toString());
        account.setUserName(username.getText().toString());
        account.setLoginPassword(login_pwd.getText().toString());
        account.setPayPassword(pay_pwd.getText().toString());

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        daoSession.getAccountDao().update(account);
        Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
        setResult(1);
        finish();
    }
}
