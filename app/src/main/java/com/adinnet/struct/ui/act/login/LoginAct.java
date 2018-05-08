package com.adinnet.struct.ui.act.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.adinnet.struct.R;
import com.adinnet.struct.base.BaseApp;
import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.comm.ToastUtil;
import com.adinnet.struct.dragger.module.LoginModule;
import com.adinnet.struct.mvp.IView;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ms.Li on 2018/3/23.
 */

public class LoginAct extends RxFragmentActivity implements IView {
    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        //注册Module
        BaseApp.getContext().getAppComponet().Login(new LoginModule(this)).in(this);
    }


    @OnClick({R.id.tv_click})
    void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                ToastUtil.showShortToast("click...");
                mLoginPresenter.login("18901780827", "1234");
                break;
            default:
                break;
        }
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
