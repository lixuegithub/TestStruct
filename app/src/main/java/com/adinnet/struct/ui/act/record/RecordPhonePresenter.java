package com.adinnet.struct.ui.act.record;

import com.adinnet.struct.bean.PhoneBean;
import com.adinnet.struct.dragger.calback.BaseCallBack;
import com.adinnet.struct.dragger.module.ApiManager;
import com.adinnet.struct.mvp.IView;

/**
 * Created by Ms.Li on 2018/3/26.
 */
public class RecordPhonePresenter {

    private ApiManager apiManager;
    private IView iView;

    public RecordPhonePresenter(ApiManager apiManager, IView iView) {
        this.apiManager = apiManager;
        this.iView = iView;
    }

    public void record(String telNo, String goodsId) {
        BaseCallBack recordPhoneCallBack = new BaseCallBack<PhoneBean>() {
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
        apiManager.recordBuyPhone(telNo, goodsId, recordPhoneCallBack);
    }

}
