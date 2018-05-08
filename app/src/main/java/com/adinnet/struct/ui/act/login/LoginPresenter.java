package com.adinnet.struct.ui.act.login;

import com.adinnet.struct.bean.PhoneBean;
import com.adinnet.struct.dragger.calback.BaseCallBack;
import com.adinnet.struct.dragger.module.ApiManager;
import com.adinnet.struct.mvp.IView;

/**
 * Created by Ms.Li on 2018/3/26.
 */
public class LoginPresenter {

    private ApiManager apiManager;
    private IView iView;

    public LoginPresenter(ApiManager apiManager, IView iView) {
        this.apiManager = apiManager;
        this.iView = iView;
    }

    public void login(String phone, String password) {
        BaseCallBack loginCallBack = new BaseCallBack<PhoneBean>() {
            @Override
            public void start() {
                iView.startRequest();
            }

            @Override
            public void next(PhoneBean phoneBean) {
                iView.requestResult(phoneBean);
            }

            @Override
            public void complete() {
                iView.completeRequest();
            }
        };
        apiManager.login(phone, password, loginCallBack);
    }

}
