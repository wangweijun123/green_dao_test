package com.le.www.green_dao_test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.greenrobot.greendao.example.Account;
import org.greenrobot.greendao.example.App;
import org.greenrobot.greendao.example.DaoSession;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by wangweijun1 on 2017/6/17.
 */

public class SecurityActivity extends AppCompatActivity {

    private LinearLayout verify_pwd_container, set_pwd_container;
    private EditText login_pwd;

    private Button bt_addAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_account);
        setTitle(R.string.certification_pwd);
        if (addPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        if (addPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        initViews();
    }

    private boolean addPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }
    String pwdPath = "/sdcard/hhh";
    private void initViews() {
        Log.d("DaoExample","initViews....:");
        verify_pwd_container = (LinearLayout)findViewById(R.id.verify_pwd_container);
        set_pwd_container = (LinearLayout)findViewById(R.id.set_pwd_container);
        File file = new File(pwdPath);
        boolean flag = file.exists();

        if (flag) {
            verify_pwd_container.setVisibility(View.VISIBLE);
            set_pwd_container.setVisibility(View.GONE);
        } else {

            verify_pwd_container.setVisibility(View.GONE);
            set_pwd_container.setVisibility(View.VISIBLE);
        }

        login_pwd = (EditText) findViewById(R.id.login_pwd);

        bt_addAccount = (Button)findViewById(R.id.bt_addAccount);
    }

    public void addAccount(View v) {
        String pwd = getPwd(pwdPath);
        Log.d("DaoExample","pwd:"+pwd);
        if (!pwd.equals(login_pwd.getText().toString())) {
            Toast.makeText(getApplicationContext(), "密码不对,请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "验证成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    public void setPwd(View v) {
        EditText pwd = (EditText)findViewById(R.id.pwd);
        EditText pwd_again = (EditText)findViewById(R.id.pwd_again);
        String pwdStr = pwd.getText().toString();
        String pwd_again_str = pwd_again.getText().toString();
        if (!pwdStr.equals(pwd_again_str)) {
            Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        savePwd(pwdPath, pwdStr);

        File file = new File(pwdPath);
        boolean flag = file.exists();
        Log.d("DaoExample","flag:"+flag);
        if (flag) {
            verify_pwd_container.setVisibility(View.VISIBLE);
            set_pwd_container.setVisibility(View.GONE);
        } else {

            verify_pwd_container.setVisibility(View.GONE);
            set_pwd_container.setVisibility(View.VISIBLE);
        }
        Toast.makeText(getApplicationContext(), "设置密码成功", Toast.LENGTH_SHORT).show();
    }


    public static void savePwd(String path, String content) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));
            out.write(content.getBytes());
            out.flush();
            Log.d("DaoExample","savePwd succes");
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPwd(String path) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File(path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            int len = -1;
            while ((len=input.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String result = baos.toString();
            Log.d("DaoExample","result:"+result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
