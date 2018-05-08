package com.adinnet.struct.dragger.module;

import com.adinnet.struct.mvp.IView;
import com.adinnet.struct.ui.act.wel.RegistePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ms.Li on 2018/3/23.
 */

@Module
public class RegisteModule {
    private IView iView;

    public RegisteModule(IView iView) {
        this.iView = iView;
    }

    @Provides
    public RegistePresenter provideRegistePresenter(ApiManager apiManager){
        return  new RegistePresenter(apiManager,iView);
    }

}
