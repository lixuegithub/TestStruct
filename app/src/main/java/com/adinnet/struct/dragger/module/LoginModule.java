package com.adinnet.struct.dragger.module;

import com.adinnet.struct.mvp.IView;
import com.adinnet.struct.ui.act.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ms.Li on 2018/3/23.
 */

@Module
public class LoginModule {
    private IView iView;

    public LoginModule(IView iView) {
        this.iView = iView;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(ApiManager apiManager){
        return  new LoginPresenter(apiManager,iView);
    }

}
