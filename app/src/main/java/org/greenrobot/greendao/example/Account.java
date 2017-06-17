package org.greenrobot.greendao.example;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangweijun1 on 2017/6/16.
 */

@Entity
public class Account {
    @Id
    private Long id;

    private String userName;
    // 如果要改表字段名字，schema version得升级，并且数据库信息全部丢失,所以尽量
    // 别改，可以增加字段
    private String loginPassword;

    private String addhhhStr;

    private String product;

    private String productName;


    @Generated(hash = 1395107403)
    public Account(Long id, String userName, String loginPassword, String addhhhStr, String product,
            String productName) {
        this.id = id;
        this.userName = userName;
        this.loginPassword = loginPassword;
        this.addhhhStr = addhhhStr;
        this.product = product;
        this.productName = productName;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "[Account] userName:"+userName+", loginPassword:"+ loginPassword;
    }

    public String getAddhhhStr() {
        return this.addhhhStr;
    }

    public void setAddhhhStr(String addhhhStr) {
        this.addhhhStr = addhhhStr;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
