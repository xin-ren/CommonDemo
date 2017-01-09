package com.utouu.cc.commondemo.application;

import android.app.Application;

import cn.utsoft.commons.toast.UTToastUtil;


/**
 * Create by 任新 on 2017/1/6 16:00
 * Function：
 * Desc：
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        UTToastUtil.initUTToastUtil(this);
    }
}
