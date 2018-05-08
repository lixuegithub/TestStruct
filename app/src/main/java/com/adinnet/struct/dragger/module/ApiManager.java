package com.adinnet.struct.dragger.module;

import android.app.Application;

import com.adinnet.struct.base.BaseResponseFunc1;
import com.adinnet.struct.bean.BookListBean;
import com.adinnet.struct.bean.RegisterBean;
import com.adinnet.struct.comm.UIUtils;
import com.adinnet.struct.dragger.calback.BaseCallBack;
import com.adinnet.struct.http.Api;
import com.adinnet.struct.rx.MySubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Api_管理类
 */
public class ApiManager {

    private Application application;
    private Api api;

    public ApiManager(Application application, Api api) {
        this.application = application;
        this.api = api;
    }

    /**读取手机状态*/
    public void recordBuyPhone(String telNo, String goodsId, BaseCallBack callBack){
        api.recordBuyPhone(1, "Android设备" + UIUtils.getLocalEthernetMacAddress(), telNo, goodsId)
                .flatMap(new BaseResponseFunc1<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Object>(application,callBack));
    }

    public void login(String mobile,String password,BaseCallBack callBack){
        String devicecode ="b698c823ef7f4dd9";
        String devicetype ="android";
        api.login(mobile,password,devicecode,devicetype)
                .flatMap(new BaseResponseFunc1<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<Object>(application,callBack));
    }


    /**注册获取设备唯一标识*/
    public void registe(BaseCallBack callBack){
        api.registe()
                .flatMap(new BaseResponseFunc1<RegisterBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Action1<RegisterBean>() {
//                    @Override
//                    public void call(RegisterBean registerBean) {
//                        LogUtil.e("xlee..."+registerBean.toString());
//                    }
//                })
                .subscribe(new MySubscriber<RegisterBean>(application,callBack));
    }

    /**获取视频列表*/
    public void bookList(BaseCallBack callBack){
        api.bookList()
                .flatMap(new BaseResponseFunc1<BookListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<BookListBean>(application,callBack));
    }

}
