package com.adinnet.struct.dragger.calback;

/**
 * Created by Ms.Li on 2018/3/26.
 */

public interface BaseCallBack<T> {
    /**请求开始*/
    void start();
    /**返回状态成功时逻辑处理*/
    void next(T t);
    /**请求完毕(success or failed)*/
    void complete();
}
