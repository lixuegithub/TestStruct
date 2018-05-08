package com.adinnet.struct.ui.act.wel;

import com.adinnet.struct.bean.RegisterBean;
import com.adinnet.struct.dragger.calback.BaseCallBack;
import com.adinnet.struct.dragger.module.ApiManager;
import com.adinnet.struct.mvp.IView;

/**
 * Created by Ms.Li on 2018/3/26.
 */
public class RegistePresenter {

    private ApiManager apiManager;
    private IView iView;

    public RegistePresenter(ApiManager apiManager, IView iView) {
        this.apiManager = apiManager;
        this.iView = iView;
    }

    public void registe() {
        BaseCallBack registeCallBack = new BaseCallBack<RegisterBean>() {
            @Override
            public void start() {
                iView.startRequest();
            }

            @Override
            public void next(RegisterBean registerBean) {
                iView.requestResult(registerBean);
            }

            @Override
            public void complete() {
                iView.completeRequest();
            }
        };
        apiManager.registe(registeCallBack);
    }

}
