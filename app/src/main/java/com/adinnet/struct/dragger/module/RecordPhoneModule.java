package com.adinnet.struct.dragger.module;

import com.adinnet.struct.mvp.IView;
import com.adinnet.struct.ui.act.record.RecordPhonePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Module
public class RecordPhoneModule {
    private IView iView;

    public RecordPhoneModule(IView iView) {
        this.iView = iView;
    }

    @Provides
    public RecordPhonePresenter provideRecordPhonePresenter(ApiManager apiManager){
        return  new RecordPhonePresenter(apiManager,iView);
    }

}
