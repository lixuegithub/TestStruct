package com.adinnet.struct.mvp;

/**
 * Created by Ms.Li on 2018/3/26.
 */

public interface IView<T> {
    void startRequest();
    void requestResult(T t);
    void completeRequest();
}
