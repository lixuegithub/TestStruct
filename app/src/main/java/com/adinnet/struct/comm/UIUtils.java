package com.adinnet.struct.comm;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.adinnet.struct.base.BaseApp;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by panlei on 16/3/17.
 */
public class UIUtils {
    private static String TAG = "UIUtils";
    public static boolean isOpen = true;

    public static Application getContext() {
        return BaseApp.getContext();
    }

    /**
     * 创建布局对象
     *
     * @param layId
     * @return
     */
    public static View inflate(int layId) {
        View view = View.inflate(getContext(), layId, null);
        return view;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isOnMainThread()) {
            runnable.run();
        } else {
            excute(runnable);
        }
    }

    /**
     * 判断程序是否在主线程运行
     *
     * @return
     */
    public static boolean isOnMainThread() {
        return BaseApp.getMyTid() == android.os.Process.myTid();
    }

    public static Handler getMainHandler() {
        return BaseApp.getHandler();
    }

    public static void excute(Runnable runnable) {
        getMainHandler().post(runnable);
    }

    public static void excuteDelay(Runnable runnable, long delayTime) {
        getMainHandler().postDelayed(runnable, delayTime);
    }

    public static void cancleRunable(Runnable runnable) {
        getMainHandler().removeCallbacks(runnable);
    }

    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        if (getContext() != null) {
            final float scale = getContext().getResources().getDisplayMetrics().density;
            return (int) (px / scale + 0.5f);
        } else {
            return 0;
        }
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 获取手机信息
     */
    public static String getDeviceId(Context context) {
        String id;
        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String serial = Build.SERIAL;
        LogUtil.i("serial:"+ serial);
        UUID uuid = UUID.randomUUID();
        LogUtil.i("uuid:"+ uuid.toString().replace("-", ""));
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;

    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString().replace(":","");
            }
        } catch (Exception ex) {
        }
        return "020000000000";
    }

    public static String getLocalEthernetMacAddress() {
        String mac=null;
        try {
            Enumeration localEnumeration= NetworkInterface.getNetworkInterfaces();

            while (localEnumeration.hasMoreElements()) {
                NetworkInterface localNetworkInterface=(NetworkInterface) localEnumeration.nextElement();
                String interfaceName=localNetworkInterface.getDisplayName();

                if (interfaceName==null) {
                    continue;
                }

                if (interfaceName.equals("eth0")) {
                    // MACAddr = convertMac(localNetworkInterface
                    // .getHardwareAddress());
                    mac=convertToMac(localNetworkInterface.getHardwareAddress());
                    if (mac!=null&&mac.startsWith("0:")) {
                        mac="0"+mac;
                    }
                    break;
                }

                // byte[] address =
                // localNetworkInterface.getHardwareAddress();
                // Log.i(TAG, "mac=" + address.toString());
                // for (int i = 0; (address != null && i < address.length);
                // i++)
                // {
                // Log.i("Debug", String.format("  : %x", address[i]));
                // }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return mac != null ? mac.replace(":","").toUpperCase() : mac;
    }

    private static String convertToMac(byte[] mac) {
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<mac.length; i++) {
            byte b=mac[i];
            int value=0;
            if (b>=0&&b<=16) {
                value=b;
                sb.append("0"+ Integer.toHexString(value));
            } else if (b>16) {
                value=b;
                sb.append(Integer.toHexString(value));
            } else {
                value=256+b;
                sb.append(Integer.toHexString(value));
            }
            if (i!=mac.length-1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    private static String getWifiMacAddr(Context context, String macAddr) {
        WifiManager wifi=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info=wifi.getConnectionInfo();
        if (null!=info) {
            String addr=info.getMacAddress();
            if (null!=addr) {
                LogUtil.d("getWifiMacAddr:"+addr);
                macAddr=addr;
            }
        }
        return macAddr;
    }


    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }

    public static String getString(int id) {
        return getResource().getString(id);
    }

    /**
     * 验证手机号码格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            ToastUtil.showShortToast("号码不能为空");
            return false;
        }
        if (!mobiles.matches(telRegex)) {
            ToastUtil.showShortToast("号码格式不正确");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static void setMargins(RelativeLayout relativeLayout, View v, int l, int t, int r, int b) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 30, 0);
        v.setLayoutParams(lp);
    }


    public static String changefloat(float text) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(text);
        return p;
    }

    public static String changeDouble(double text) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(text);
        return p;
    }

    public static String dateDiff(long startTime, long endTime) throws ParseException {
        //按照传入的格式生成一个simpledateformate对象
        // SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;try {
        //获得两个时间的毫秒时间差异
        long diff = endTime - startTime;
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        long sec = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
        return day + "=" + hour + "=" + min + "=" + sec;
    }



}
