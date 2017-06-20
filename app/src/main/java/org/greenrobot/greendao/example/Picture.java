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
    private Long pictureId;

    private String icon;

    @Generated(hash = 1734763618)
    public Picture(Long pictureId, String icon) {
        this.pictureId = pictureId;
        this.icon = icon;
    }

    @Generated(hash = 1602548376)
    public Picture() {
    }

    public Long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
