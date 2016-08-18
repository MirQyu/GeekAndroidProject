package com.geekband.Test01;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by MirQ on 16/7/5.
 */
public class UserInfo implements Serializable{

    private String mUserName;

    private int mAge;

    public UserInfo(String mUserName, int mAge) {
        this.mUserName = mUserName;
        this.mAge = mAge;

    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }
}
