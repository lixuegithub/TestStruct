package com.adinnet.struct.base;

import com.adinnet.struct.modle.BaseResponse;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Ms.Li on 2018/3/26.
 */

public class BaseResponseFunc1<T> implements Func1<BaseResponse<T>, Observable<T>> {
    @Override
    public Observable<T> call(BaseResponse<T> tBaseResponse) {
        switch (tBaseResponse.getCode()) {
            case 4000:
                return Observable.error(new Throwable("请求参数异常"));
            case 4001:
                return Observable.error(new Throwable("用户未授权"));
            case 4002:
                return Observable.error(new Throwable("无权限查看"));
            case 4003:
                return Observable.error(new Throwable("存在冲突，请求无法完成，重新提交新的请求"));
            case 4004:
                return Observable.error(new Throwable("当前资源被锁定"));
            case 500:
                return Observable.error(new Throwable("服务器异常"));
            case 404:
                return Observable.error(new Throwable("资源不存在"));
            case 200://正常返回数据,发射data数据
                return Observable.just(tBaseResponse.getData());
        }
        return Observable.error(new Throwable("未知错误"));
    }
}
