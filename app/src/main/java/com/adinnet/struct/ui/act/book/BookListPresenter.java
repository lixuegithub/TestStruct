package com.adinnet.struct.ui.act.book;

import com.adinnet.struct.bean.BookListBean;
import com.adinnet.struct.dragger.calback.BaseCallBack;
import com.adinnet.struct.dragger.module.ApiManager;
import com.adinnet.struct.mvp.IView;

/**
 * Created by Ms.Li on 2018/3/26.
 */
public class BookListPresenter {

    private ApiManager apiManager;
    private IView iView;

    public BookListPresenter(ApiManager apiManager, IView iView) {
        this.apiManager = apiManager;
        this.iView = iView;
    }

    public void bookList() {
        BaseCallBack recordPhoneCallBack = new BaseCallBack<BookListBean>() {
            @Override
            public void start() {
                iView.startRequest();
            }

            @Override
            public void next(BookListBean object) {
                iView.requestResult(object);
            }

            @Override
            public void complete() {
                iView.completeRequest();
            }
        };
        apiManager.bookList(recordPhoneCallBack);
    }

}
