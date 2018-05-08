package com.adinnet.struct.tool;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Ms.Li on 2018/4/24.
 */

public class SystemUtils {

    /**获取版本名*/
    public static String getVersionName(Context context){
        return getPackageInfo(context).versionName;
    }

    /**获取版本Code*/
    public static int getVersionCode(Context context){
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(Context context){
        PackageInfo pi = null;
        PackageManager pm = context.getPackageManager();
        try {
            pi = pm.getPackageInfo(context.getPackageName(),PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**获取appName*/
    public static String getAppName(Context mContent,int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) mContent.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = mContent.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
