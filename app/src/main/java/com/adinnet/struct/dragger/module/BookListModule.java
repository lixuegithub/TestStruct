package com.adinnet.struct.dragger.module;

import com.adinnet.struct.mvp.IView;
import com.adinnet.struct.ui.act.book.BookListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Module
public class BookListModule {
    private IView iView;

    public BookListModule(IView iView) {
        this.iView = iView;
    }

    @Provides
    public BookListPresenter provideBookListPresenter(ApiManager apiManager){
        return  new BookListPresenter(apiManager,iView);
    }

}
