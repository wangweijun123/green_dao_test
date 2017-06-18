package org.greenrobot.greendao.example;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by wangweijun1 on 2017/6/16.
 * 如果要改表字段名字，schema version得升级，并且数据库信息全部丢失,所以尽量别改，可以增加字段
 */

@Entity
public class Account implements Serializable{
    public static final long serialVersionUID = 536871008L;
    @Id
    private Long id;
    /** 平台名称 */
    private String platformName;
    /** 官网 */
    private String officialWeb;
    /** 用户名 */
    private String userName;
    /** 登录密码 */
    private String loginPassword;
    /** 支付密码 */
    private String payPassword;

    @Generated(hash = 1774643570)
    public Account(Long id, String platformName, String officialWeb, String userName,
            String loginPassword, String payPassword) {
        this.id = id;
        this.platformName = platformName;
        this.officialWeb = officialWeb;
        this.userName = userName;
        this.loginPassword = loginPassword;
        this.payPassword = payPassword;
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
        return "[Account] id:"+id+", userName:"+ userName+", loginPassword:"+ loginPassword
                +", payPassword:"+payPassword+", platformName:"+platformName+", officialWeb:"+officialWeb;
    }

    public String getPlatformName() {
        return this.platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getOfficialWeb() {
        return this.officialWeb;
    }

    public void setOfficialWeb(String officialWeb) {
        this.officialWeb = officialWeb;
    }

    public String getPayPassword() {
        return this.payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

}
