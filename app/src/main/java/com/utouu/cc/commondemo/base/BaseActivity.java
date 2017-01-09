package com.utouu.cc.commondemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Create by 任新 on 2017/1/6 10:51
 * Function：基类
 * Desc：主要用于统一设置每个activity头部的bar
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    /**
     * 获得布局文件id
     */
    public abstract int getLayoutId();

    /**
     * 设置bar
     *
     * @param toolbar toolbar控件
     * @param title   标题
     */
    protected void setToolbar(Toolbar toolbar, String title) {
        //设置标题
        toolbar.setTitle(title);
        //设定toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //设定toolbar取消监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
