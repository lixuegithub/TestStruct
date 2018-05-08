package com.adinnet.struct.rx;

import android.app.Application;

import com.adinnet.struct.comm.ToastUtil;
import com.adinnet.struct.dragger.calback.BaseCallBack;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Ms.Li on 2018/3/26.
 */
public class MySubscriber<T> extends Subscriber<T> {
    private Application application;

    private BaseCallBack<T> baseCallBack;

    public MySubscriber(Application application, BaseCallBack callBack) {
        this.baseCallBack = callBack;
        this.application = application;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (baseCallBack != null) {
            baseCallBack.start();
        }
    }

    @Override
    public void onCompleted() {
        if (baseCallBack != null) {
            baseCallBack.complete();
        }
        // 数据完成后取消订阅。释放资源，避免内存泄漏.Subscriber内部在onNext和onError后会自动取消订阅
    }

    @Override
    public void onError(Throwable e) {
        //异常逻辑处理
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast("连接中断，请检查您的网络状态");
        } else {
            ToastUtil.showLongToast("error:" + e.getMessage());
        }
        if (baseCallBack != null) {
            baseCallBack.complete();
        }
    }

    @Override
    public void onNext(T t) {
        if (baseCallBack != null) {
            baseCallBack.next(t);
        }
    }
}
