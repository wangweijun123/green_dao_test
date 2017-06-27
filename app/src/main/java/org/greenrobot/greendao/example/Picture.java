package org.greenrobot.greendao.example;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangweijun1 on 2017/6/20.
 */

@Entity
public class Picture {
    @Id
    private Long id;

    private String icon;

    @Generated(hash = 567752782)
    public Picture(Long id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    @Generated(hash = 1602548376)
    public Picture() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
