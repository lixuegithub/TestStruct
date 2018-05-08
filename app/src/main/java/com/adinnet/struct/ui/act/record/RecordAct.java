package com.adinnet.struct.ui.act.record;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.adinnet.struct.R;
import com.adinnet.struct.base.BaseApp;
import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.dragger.module.RecordPhoneModule;
import com.adinnet.struct.mvp.IView;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 测试
 */
public class RecordAct extends RxFragmentActivity implements IView {
    @Inject
    RecordPhonePresenter mRecordPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wel);
        ButterKnife.bind(this);
        //注册Module
        BaseApp.getContext().getAppComponet().record(new RecordPhoneModule(this)).in(this);
        mRecordPresenter.record("18201908190","Usd123");
    }

    @Override
    public void startRequest() {
        LogUtil.e("startRequest...");
    }

    @Override
    public void requestResult(Object o) {
        LogUtil.e("requestResult..." + o.toString());
    }

    @Override
    public void completeRequest() {
        LogUtil.e("completeRequest...");
    }
}
