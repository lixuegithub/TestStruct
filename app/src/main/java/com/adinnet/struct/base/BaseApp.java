package com.adinnet.struct.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.comm.ToastUtil;
import com.adinnet.struct.dragger.componet.AppComponet;
import com.adinnet.struct.dragger.componet.DaggerAppComponet;
import com.adinnet.struct.dragger.module.AppModule;
import com.adinnet.struct.tool.SystemUtils;
import com.antfortune.freeline.FreelineCore;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.Bugly;

import org.litepal.LitePalApplication;

/**
 * Created by Ms.Li on 2018/3/19.
 */
public class BaseApp extends LitePalApplication {
    private static final String TAG = "BaseApp";
    private AppComponet appComponent;

    private static BaseApp mContext;
    private static int myTid;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponet.builder().appModule(new AppModule(this)).build();
        mContext = this;
        mHandler = new Handler();
        //当前线程
        myTid = android.os.Process.myTid();
        FreelineCore.init(this);
        initBugly();
        initMob();
    }

    private void initMob() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        int pid = android.os.Process.myPid();
        String processAppName = SystemUtils.getAppName(this,pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            LogUtil.e("app....enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        try {
//            initHotFix();
//        }catch (ClassCastException e){
//            e.printStackTrace();
//        }
    }

    private void initHotFix() {
        // initialize最好放在attachBaseContext最前面
        SophixManager.getInstance().setContext(this)
                .setAppVersion(Integer.toString(SystemUtils.getVersionCode(this)))
//                .setAesKey("wangkui198707099")
                .setAesKey("structapp1234567")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            ToastUtil.showShortToast("热更新下载完成，需要重启才可生效");
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            //SophixManager.getInstance().killProcessSafely();
                            restartApplication();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }

    /**重启应用*/
    private void restartApplication() {
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /**添加bugly检测异常*/
    private void initBugly()  {
        Bugly.init(this, "1ba0623d6d", false);
    }

    /**得到全局appComponent*/
    public AppComponet getAppComponet(){
        return appComponent;
    }

    public static BaseApp getContext() {
        return mContext;
    }

    public static int getMyTid() {
        return myTid;
    }

    public static Handler getHandler() {
        return mHandler;
    }
}
