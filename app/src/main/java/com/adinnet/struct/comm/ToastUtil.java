package com.adinnet.struct.comm;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public final class ToastUtil {


    private static Toast sToast;
    private static Context sContext = UIUtils.getContext();
    public static boolean isDebug = true;

    /**
     * show toast
     *
     * @param text content value's string
     */
    public static void showShortToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }


    /**
     * show toast
     *
     * @param text content value's string
     */
    public static void showLongToast(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }


    /**
     * show toast
     *
     * @param text     content value's string
     * @param duration how long the toast should be show
     */
    public static void showToast(String text, int duration) {
        if (isDebug) {
            if (sToast == null) {
                sToast = Toast.makeText(sContext, text, duration);
            } else {
                sToast.setText(text);
            }
            sToast.setGravity(Gravity.CENTER, 0, 0);
            sToast.show();
        }
    }
}
