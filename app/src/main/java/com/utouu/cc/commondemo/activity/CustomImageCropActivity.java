package com.utouu.cc.commondemo.activity;

import android.view.View;
import android.widget.TextView;

import com.utouu.cc.commondemo.R;

import cn.utsoft.commons.cropper.CropImageView;
import cn.utsoft.commons.cropper.UTBaseCropActivity;

import static com.utouu.cc.commondemo.R.layout.activity_custom_image_crop;

public class CustomImageCropActivity extends UTBaseCropActivity implements View.OnClickListener {

    private TextView mTvCancle; //取消
    private TextView mIvRotate; //旋转
    private TextView mTvCut; //剪裁
    private CropImageView mCrpImageView;

    @Override
    protected int getLayoutResId() {
        return activity_custom_image_crop;
    }

    @Override
    protected void initView() {
        mTvCancle = (TextView) findViewById(R.id.tv_cancel);
        mIvRotate = (TextView) findViewById(R.id.iv_rotate);
        mTvCut = (TextView) findViewById(R.id.tv_cut);
        mCrpImageView = (CropImageView) findViewById(R.id.cropImageView);

        mTvCancle.setOnClickListener(this);
        mTvCut.setOnClickListener(this);
        mIvRotate.setOnClickListener(this);
    }

    @Override
    protected CropImageView getCropImageView() {
        return mCrpImageView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                //取消剪裁（父类中提供的方法）
                setResultCancel();
                break;
            case R.id.iv_rotate:
                //旋转图片（父类中提供的方法）
                rotateImage(90);
                break;
            case R.id.tv_cut:
                //确认剪裁（父类中提供的方法）
                cropImage();
                break;
        }
    }
}
