package com.utouu.cc.commondemo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 任新 on 2017/1/6 11:26.
 * Function: Toast辅助类
 * Desc:
 */
public class ToastUtil {
    private static Toast mToast;

    public static void showShortToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
