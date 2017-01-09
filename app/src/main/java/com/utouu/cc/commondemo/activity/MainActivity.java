package com.utouu.cc.commondemo.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import com.utouu.cc.commondemo.R;
import com.utouu.cc.commondemo.base.BaseActivity;
import com.utouu.cc.commondemo.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.utsoft.commons.cropper.CropResultListener;
import cn.utsoft.commons.cropper.UTCropManager;
import cn.utsoft.commons.imgbrowser.helper.UTImgBrowserHelper;
import cn.utsoft.commons.qrscanner.MakeQRCodeListener;
import cn.utsoft.commons.qrscanner.QRCodeOption;
import cn.utsoft.commons.qrscanner.ScanResultListener;
import cn.utsoft.commons.qrscanner.UTScannerManager;
import cn.utsoft.commons.toast.UTToast;
import cn.utsoft.commons.toast.UTToastUtil;

import static android.R.attr.duration;
import static android.R.attr.visibility;

/**
 * Created by 任新 on 2017/1/6 10:53.
 * Function:成都公共模块测试主界面
 * Desc: 主要有以下公共模块：1，二维码扫描；2，图片裁剪；3，弱提示框；4，查看大图
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    Toolbar mToolbar;
    @BindView(R.id.iv_scanImg)
    ImageButton iv_scanImg;

    private UTCropManager mCropManager;
    private boolean mIsOval = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("成都公共模块测试");
        setSupportActionBar(mToolbar);
    }

    /**
     * 父类方法：绑定布局文件
     *
     * @return 返回布局id
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * ButterKnife注解监听
     *
     * @param view
     */
    @OnClick({R.id.btn_scanning_mainActivity, R.id.btn_imageCrop_mainActivity, R.id.btn_weakHint_mainActivity, R.id.btn_scanImage_mainActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            //二维码扫描
            case R.id.btn_scanning_mainActivity:
                iv_scanImg.setVisibility(View.VISIBLE);
                //调用默认的二维码扫描
                //defaultUIScan();
                //调用自定义的二维码扫描
                customUIScan();
                createChineseQRCodeWithLogo();
                break;
            //图片裁剪
            case R.id.btn_imageCrop_mainActivity:
                iv_scanImg.setVisibility(View.GONE);
                //调用默认的图片裁剪
                defaulCropImage();
                //调用自定义的图片裁剪
//                customCropImage();
                break;
            //弱提示框
            case R.id.btn_weakHint_mainActivity:
                iv_scanImg.setVisibility(View.GONE);
                //UTSOFT快速使用
                //UTToastUtil.success("测试成功");
                //默认弱提示框显示
                //UTToast.makeUTToast(getApplicationContext(),"测试成功",duration).show();
                //自定义弱提示框显示
                UTToast.makeUTToast(getApplicationContext(), "测试成功", UTToast.LENGTH_SHORT)
                        .setToastGravity(Gravity.BOTTOM, 0, 80)
                        .setTextImage(R.drawable.success)
                        .setToastTextColor(Color.WHITE)
                        .setBackgroundColor(Color.RED)
                        .setBackgroundAlpha(125)
                        .setBackgroundRadius(10)
                        .setTextImageSize(50, 50)
                        .setTextImageLocation(UTToast.RIGHT)
                        .setImagePadding(20)
                        .show();
                break;
            //查看大图
            case R.id.btn_scanImage_mainActivity:
                iv_scanImg.setVisibility(View.GONE);
                UTImgBrowserHelper helper = new UTImgBrowserHelper(this);
                helper.addImageView(iv_scanImg,"https://www.baidu.com/img/bd_logo1.png");
                helper.startPreActivity(0);
                break;
        }
    }

    //默认的二维码扫描
    private void defaultUIScan() {
        UTScannerManager.getIns().toScanActivity(this, new ScanResultListener() {
            //扫描结果回调
            @Override
            public void onSuccess(String result) {
                ToastUtil.showShortToast(MainActivity.this, result);
            }

            @Override
            public void onFailure() {
                ToastUtil.showShortToast(MainActivity.this, "扫描失败");
            }

            @Override
            public void onCancel() {
                ToastUtil.showShortToast(MainActivity.this, "扫描取消");
            }
        });
    }

    //自定义的二维码扫描
    private void customUIScan() {
        UTScannerManager.getIns().toScanActivity(this, CustomUIScanActivity.class, new ScanResultListener() {
            //扫描结果回调
            @Override
            public void onSuccess(String result) {
                ToastUtil.showShortToast(MainActivity.this, result);
            }

            @Override
            public void onFailure() {
                ToastUtil.showShortToast(MainActivity.this, "扫描失败");
            }

            @Override
            public void onCancel() {
                ToastUtil.showShortToast(MainActivity.this, "扫描取消");
            }
        });
    }

    //生成带logo的二维码图片
    private void createChineseQRCodeWithLogo() {
        //获取要添加到二维码中的图片对象
        Bitmap logoBitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.ic_launcher);
        //创建生成二维码图片的配置对象
        QRCodeOption option = new QRCodeOption("悠唐网络科技有限公司", 100, logoBitmap, UTScannerManager.LogoSize.LARGE);
        //传入配置对象，和回调监听
        UTScannerManager.getIns().makeQRCode(option, new MakeQRCodeListener() {
            //生成二维码图片的回调
            @Override
            public void onSuccess(Bitmap bitmap) {
                iv_scanImg.setImageBitmap(bitmap);
                ToastUtil.showShortToast(MainActivity.this, "Success");
            }

            @Override
            public void onFailure() {
                ToastUtil.showShortToast(MainActivity.this, "Failure");
            }
        });
    }

    //默认裁剪图片
    private void defaulCropImage() {
        //创建UTCropManager对象
        mCropManager = new UTCropManager(this);
        //调用剪裁方法
        mCropManager.crop(this, null, UTCropManager.SourceType.GALLERY, false, new CropResultListener() {
            @Override
            public void onSuccess(Uri uri) {
//                File file = new File(URI.create(uri.toString()));
//                if (mIsOval) GlideManager.loadRoundImg(file, mIvAvater);
//                else GlideManager.loadImg(file, mIvAvater);
            }

            @Override
            public void onFailure(String reason) {
                ToastUtil.showShortToast(MainActivity.this, reason);
            }
        });
    }

    //自定义裁剪图片
    private void customCropImage(){
        //通过UTCropManager对象调用crop()方法，传入自定义剪裁界面的CustomCropUIActivity
        mCropManager.crop(this, CustomImageCropActivity.class, UTCropManager.SourceType.ALL, false, new CropResultListener() {
            @Override
            public void onSuccess(Uri uri) {

            }

            @Override
            public void onFailure(String s) {

            }
        });
    }

    //注意！！需要重写Activity的onActivityResult方法，并调用cropManager.handleCropResult方法。否则不会回调剪裁结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCropManager.handleCropResult(requestCode, resultCode, data);
    }
}

