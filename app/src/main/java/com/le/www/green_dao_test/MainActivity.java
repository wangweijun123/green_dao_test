package com.le.www.green_dao_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.greendao.example.Account;
import org.greenrobot.greendao.example.AccountDao;
import org.greenrobot.greendao.example.AccountsAdapter;
import org.greenrobot.greendao.example.App;
import org.greenrobot.greendao.example.DaoSession;
import org.greenrobot.greendao.example.Note;
import org.greenrobot.greendao.example.NotesAdapter;
import org.greenrobot.greendao.query.Query;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AccountDao accountDao;
    private Query<Account> accountsQuery;
    private AccountsAdapter accountsAdapter;

    private static final int REQUEST_CODE_ADD_ACCOUNT = 1;
    private static final int REQUEST_CODE_UPDATE_ACCOUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.account_list);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        accountDao = daoSession.getAccountDao();

        setUpViews();
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        accountsAdapter = new AccountsAdapter(getApplicationContext(), noteClickListener);
        recyclerView.setAdapter(accountsAdapter);

        getAccountList();
    }

    AccountsAdapter.AccountClickListener noteClickListener = new AccountsAdapter.AccountClickListener() {
        @Override
        public void onItemClick(int position) {
            Account account = accountsAdapter.getAccount(position);
            Intent intent = new Intent(getApplicationContext(), AccountDetailActivity.class);
            intent.putExtra("account", account);
            startActivityForResult(intent, REQUEST_CODE_UPDATE_ACCOUNT);
//            updateNotes();
        }

        @Override
        public void onLongClick(int position) {
            account = accountsAdapter.getAccount(position);
            dialog();
        }
    };

    Account account;

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确认删除" + account.getPlatformName() + "账号吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                accountDao.delete(account);
                getAccountList();
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    public void addAccount(View v) {
        Intent intent = new Intent(getApplicationContext(), AddAccountActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_ACCOUNT);
    }

    public void getAccountList() {
        accountsQuery = accountDao.queryBuilder().build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
        accountsAdapter.setAccounts(accounts);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DaoExample", "requestCode:" + requestCode + ", resultCode:" + resultCode);
        switch (requestCode) {
            case REQUEST_CODE_ADD_ACCOUNT:
                getAccountList();
                break;
            case REQUEST_CODE_UPDATE_ACCOUNT:
                if (resultCode == 1) {// success
                    getAccountList();
                }
                break;
        }
    }
}
