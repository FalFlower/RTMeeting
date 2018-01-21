package com.example.dell.rtmeeting.customClass;

/**
 * 会议类
 *  数据成员有
 *      总人数
 *      封面图片id
 * Created by dell on 2018/1/16.
 */

public class Meeting {
    private int headcount;
    private int imageId;

    public Meeting(int headcount, int imageId) {
        this.headcount = headcount;
        this.imageId = imageId;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
