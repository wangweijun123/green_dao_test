package com.le.www.green_dao_test;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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
import org.greenrobot.greendao.example.Picture;
import org.greenrobot.greendao.example.User;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;
import org.greenrobot.greendao.test.entityannotation.Customer;
import org.greenrobot.greendao.test.entityannotation.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private AccountDao accountDao;
    private Query<Account> accountsQuery;

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

        getAccountList();
    }


    Account account;


    public void queryList(View v) {
        // 结果排序
        accountsQuery = accountDao.queryBuilder().build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
    }

    public void queryListSize(View v) {
        // 结果排序
        accountsQuery = accountDao.queryBuilder().build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
    }


    public void deleteAll(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Log.d("DaoExample","使用greenDAO开始");
        long start = System.currentTimeMillis();
        daoSession.getAccountDao().deleteAll();
        Log.d("DaoExample","使用greenDAO spend time :"+(System.currentTimeMillis()-start));
    }

    public void queryListOrderPlatform(View v) {
        // 结果排序
        accountsQuery = accountDao.queryBuilder().orderAsc(AccountDao.Properties.PlatformName).build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
    }

    public void queryListAndWhere(View v) {
        // 结果排序
        accountsQuery = accountDao.queryBuilder()
                .where(AccountDao.Properties.Id.between(2, 5)).build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
    }

    public void queryListAndMoreWhere(View v) {
        // 结果排序
        accountsQuery = accountDao.queryBuilder()
                .where(AccountDao.Properties.Id.between(2, 5),
                        AccountDao.Properties.PlatformName.like("c")).build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
    }



    public void addAccount(View v) {
        Intent intent = new Intent(getApplicationContext(), AddAccountActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_ACCOUNT);
    }
    int count = 10000;
    public void addManyAccount(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Log.d("DaoExample","使用greenDAO开始");
        long start = System.currentTimeMillis();
        List<Account> list = new ArrayList<>();
        for (int i=0; i<count; i++) {
            Account account = new Account();
            account.setPlatformName(new Random(1000).nextDouble()+"");
            account.setOfficialWeb(new Random(100).nextInt()+"");
            account.setUserName(new Random(100).nextInt()+"");
            account.setLoginPassword(new Random(100).nextInt()+"");
            account.setPayPassword(new Random(100).nextInt()+"");
            list.add(account);
            long id = daoSession.getAccountDao().insert(account);
//            Log.d("DaoExample", id + " Inserted new account, ID: " + account.getId());
        }
        Log.d("DaoExample","使用greenDAO spend time :"+(System.currentTimeMillis()-start));
    }
    // batch
    public void addManyAccountUseBatch(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Log.d("DaoExample","使用greenDAO开始");
        long start = System.currentTimeMillis();
        List<Account> list = new ArrayList<>();
        for (int i=0; i<count; i++) {
            Account account = new Account();
            account.setPlatformName(new Random(1000).nextDouble()+"");
            account.setOfficialWeb(new Random(100).nextInt()+"");
            account.setUserName(new Random(100).nextInt()+"");
            account.setLoginPassword(new Random(100).nextInt()+"");
            account.setPayPassword(new Random(100).nextInt()+"");
            list.add(account);
        }
        // 开启事务批量插入
        daoSession.getAccountDao().insertInTx(list);

        Log.d("DaoExample","使用greenDAO spend time :"+(System.currentTimeMillis()-start));
    }

    public void addManyAccountUsePreMethod(View v) {
        SQLiteDatabase db = AppUpdateIgnoreHelper.getInstance(getApplicationContext()).getWritableDatabase();
        Log.d("DaoExample","使用原有方式 开始");
        long start = System.currentTimeMillis();
        for (int i=0; i<count; i++) {
            Account account = new Account();
            account.setPlatformName(new Random(1000).nextDouble()+"");
            account.setOfficialWeb(new Random(100).nextInt()+"");
            account.setUserName(new Random(100).nextInt()+"");
            account.setLoginPassword(new Random(100).nextInt()+"");
            account.setPayPassword(new Random(100).nextInt()+"");
            ContentValues values = createContentValues(account);
            db.insert("ACCOUNT", null, values);
        }
        Log.d("DaoExample","使用原有方式 spend time :"+(System.currentTimeMillis()-start));
    }

    public void acompileStatementAndBeginTx(View v) {
        Log.d("DaoExample","使用compile statement开始");
        long start = System.currentTimeMillis();
        List<Account> list = new ArrayList<>();
        for (int i=0; i<count; i++) {
            Account account = new Account();
            account.setPlatformName(new Random(1000).nextDouble()+"");
            account.setOfficialWeb(new Random(100).nextInt()+"");
            account.setUserName(new Random(100).nextInt()+"");
            account.setLoginPassword(new Random(100).nextInt()+"");
            account.setPayPassword(new Random(100).nextInt()+"");
            list.add(account);
        }
        insertBySql(list);
        Log.d("DaoExample","使用compile statement spend time :"+(System.currentTimeMillis()-start));
    }



    /**
     * @param list
     * @return
     */
    public  boolean insertBySql(List<Account> list) {

        SQLiteDatabase db = AppUpdateIgnoreHelper.getInstance(getApplicationContext()).getWritableDatabase();
        try {
            String sql = "insert into " + "ACCOUNT" + "("
                    + "PLATFORM_NAME"  + ","// 包名
                    + "OFFICIAL_WEB" + ","// 账号
                    + "USER_NAME" + ","// 来源
                    + "LOGIN_PASSWORD" + ","// PC mac 地址
                    + "PAY_PASSWORD"
                    + ") " + "values(?,?,?,?,?)";
            SQLiteStatement stat = db.compileStatement(sql);
            db.beginTransaction();
            for (Account remoteAppInfo : list) {
                stat.bindString(1, remoteAppInfo.getPlatformName());
                stat.bindString(2, remoteAppInfo.getOfficialWeb());
                stat.bindString(3, remoteAppInfo.getUserName());
                stat.bindString(4, remoteAppInfo.getLoginPassword());
                stat.bindString(5, remoteAppInfo.getPayPassword());
                long rowId = stat.executeInsert();
                Log.d("DaoExample", " rowId:"+rowId);
                if (rowId < 0) {
                    return false;
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != db) {
                    db.endTransaction();
                    db.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    long pictureId;
    long userId;
    public void toOneTest(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Picture picture = new Picture();
        picture.setIcon("http://cixon");
        pictureId = daoSession.getPictureDao().insert(picture);

        User user = new User();
        user.setName("wxj");
        user.setPicture(picture);

        userId = daoSession.getUserDao().insert(user);

    }

    public void queryToOneTest(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        Query<User> query = daoSession.getUserDao().queryBuilder().where(new WhereCondition.StringCondition("_id="+userId)).build();
        List<User> users = query.list();
        Log.d("DaoExample","users size:"+users.size());
        for (int i=0; i<users.size(); i++) {
            User user = users.get(i);
            Log.d("DaoExample",user.getId() + ", "+user.getName()+
            ", Picture id:"+user.getPicture().getId());
        }
    }




    /**
     * CREATE TABLE "ACCOUNT" (
     *          "_id" INTEGER PRIMARY KEY AUTOINCREMENT ,
     *          "PLATFORM_NAME" TEXT,
     *          "OFFICIAL_WEB" TEXT,
     *          "USER_NAME" TEXT NOT NULL ,
     *          "LOGIN_PASSWORD" TEXT NOT NULL ,
     *          "PAY_PASSWORD" TEXT NOT NULL ,
     *          "PRODUCT_NAME" TEXT
     * );
     * @return
     */

    private static ContentValues createContentValues(Account account) {
        ContentValues values = new ContentValues();
        values.put("PLATFORM_NAME", account.getPlatformName());
        values.put("OFFICIAL_WEB", account.getOfficialWeb());
        values.put("USER_NAME", account.getUserName());
        values.put("LOGIN_PASSWORD", account.getLoginPassword());
        values.put("PAY_PASSWORD", account.getPayPassword());
        return values;
    }


    public void getAccountList() {
        // 结果排序
        accountsQuery = accountDao.queryBuilder().orderAsc(AccountDao.Properties.PlatformName).build();
        List<Account> accounts = accountsQuery.list();
        int size = accounts.size();
        Log.d("DaoExample", "accounts size: " + size);
        for (int i = 0; i < size; i++) {
            Log.d("DaoExample", accounts.get(i).toString());
        }
    }


    public void testCustomerToOrders(View v){

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Customer customer = new Customer(null, "greenrobot");
        daoSession.getCustomerDao().insert(customer);

        addOrderToCustomer(customer);
        addOrderToCustomer(customer);

        List<Order> orders = customer.getOrders();
        Log.d("DaoExample", "orders size: " + orders.size());
    }

    public void testOrderToCustomer(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Customer customer = new Customer(null, "greenrobot"+new Random().nextInt(100));
        daoSession.insert(customer);

        Order order = addOrderToCustomer(customer);
        Customer customer2 = order.getCustomer();
        Log.d("DaoExample",customer.getId() + ", "+ customer2.getId());
        Log.d("DaoExample", "customer == customer2" + (customer == customer2));
    }


    public void testUpdate(View v) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Customer customer = new Customer(null, "greenrobot"+new Random().nextInt(100));
        daoSession.insert(customer);

        addOrderToCustomer(customer);
        List<Order> orders = customer.getOrders();

        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        daoSession.insert(newOrder);
        orders.add(newOrder);
        Log.d("DaoExample", "orders.size() :" + orders.size() );
        customer.resetOrders();
        List<Order> orders2 = customer.getOrders();
        Log.d("DaoExample", "orders.size() :" + orders.size() +", orders2.size():"+orders2.size());
    }


    private Order addOrderToCustomer(Customer customer) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        Date date = new Date(System.currentTimeMillis() - ((long) (Math.random() * 1000 * 60 * 60 * 24 * 365)));
        Order order = new Order(null, date, customer.getId());
        daoSession.getOrderDao().insert(order);
        return order;
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
