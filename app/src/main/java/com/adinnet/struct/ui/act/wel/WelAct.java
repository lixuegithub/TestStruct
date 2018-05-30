package com.adinnet.struct.ui.act.wel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.adinnet.struct.R;
import com.adinnet.struct.base.BaseApp;
import com.adinnet.struct.bean.RegisterBean;
import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.comm.ToastUtil;
import com.adinnet.struct.dragger.module.RegisteModule;
import com.adinnet.struct.easemob.EaseMobAct;
import com.adinnet.struct.mvp.IView;
import com.adinnet.struct.testutils.TestUtilAct;
import com.adinnet.struct.tool.SystemUtils;
import com.adinnet.struct.ui.act.book.BookListAct;
import com.adinnet.struct.ui.act.testact.TestActBAct;
import com.taobao.sophix.SophixManager;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;

/**
 * 引导页
 */
public class WelAct extends RxFragmentActivity implements IView {

    private static final String TAG = WelAct.class.getSimpleName();
    @BindView(R.id.tv_versioncode)
    TextView tvVersionCode;

    @Inject
    RegistePresenter mRegistePresenter;

    @DebugLog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wel);
        LogUtil.e(TAG+"...onCreate...");
        ButterKnife.bind(this);
        //注册Module
        BaseApp.getContext().getAppComponet().registe(new RegisteModule(this))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   .in(this);
        //TODO 在项目入口，推荐在MainActivity 的onCreate方法中获取是否有patch，
        // 不能放在application里面这个补丁是需要我们代码主动去拿，在onCreate中调用SDK里面方法
        SophixManager.getInstance().queryAndLoadNewPatch();
        LogUtil.e("queryAndLoadNewPatch查询热更新一次....");
        //显示当前版本号
        tvVersionCode.setText(tvVersionCode.getText().toString()+SystemUtils.getVersionCode(this)+"更改Bug");
    }

    @OnClick({R.id.tv_toeasemob,R.id.tv_wel,R.id.tv_toTestUtils,R.id.tv_testact})
    @DebugLog
    void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_toeasemob:
                startActivity(new Intent(this, EaseMobAct.class));
                break;
            case R.id.tv_wel:
                mRegistePresenter.registe();
                break;
            case R.id.tv_toTestUtils:
                startActivity(new Intent(this, TestUtilAct.class));
                break;
            case R.id.tv_testact:
                startActivity(new Intent(this, TestActBAct.class));
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
        if (o==null){return;}
        LogUtil.e("requestResult..." + o.toString());
        RegisterBean registerBean = (RegisterBean) o;
        ToastUtil.showShortToast("注册成功."+registerBean.getDeviceId());
        startActivity(new Intent(this, BookListAct.class));
    }

    @Override
    public void completeRequest() {
        LogUtil.e("completeRequest...");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e(TAG+"...onResume...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e(TAG+"...onStart...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e(TAG+"...onPause...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e(TAG+"...onRestart...");
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e(TAG+"...onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG+"...onDestroy...");
    }
}
