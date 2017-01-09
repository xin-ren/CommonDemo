package com.utouu.cc.commondemo.activity;

import android.view.View;

import com.utouu.cc.commondemo.R;

import cn.utsoft.commons.qrscanner.ScannerView;
import cn.utsoft.commons.qrscanner.UTBaseScanActivity;
import cn.utsoft.commons.qrscanner.UTScannerView;

public class CustomUIScanActivity extends UTBaseScanActivity {

    private UTScannerView mScannerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_custom_uiscan;
    }

    @Override
    protected void initView() {
        mScannerView = (UTScannerView) findViewById(R.id.sv_custom);
    }

    @Override
    protected ScannerView getScannerView() {
        return mScannerView;
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            //开启闪光灯（父类中提供的方法）
            case R.id.iv_flashlight:
                toggleFlashlight();
                break;
            //打开相册选择图片（父类中提供的方法）
            case R.id.iv_gallery:
                openGallery();
                break;
            //取消扫描
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
