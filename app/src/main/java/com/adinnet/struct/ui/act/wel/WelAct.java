package com.adinnet.struct.ui.act.wel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
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
import com.adinnet.struct.tool.FilterTextWatcher;
import com.adinnet.struct.tool.SystemUtils;
import com.adinnet.struct.ui.act.arouter.ARouterBean;
import com.adinnet.struct.ui.act.book.BookListAct;
import com.adinnet.struct.ui.act.testact.TestActBAct;
import com.alibaba.android.arouter.launcher.ARouter;
import com.taobao.sophix.SophixManager;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;

import static com.adinnet.struct.tool.Constants.ACTIVITY_URL_AROUTER;
import static com.adinnet.struct.tool.Constants.ACTIVITY_URL_CHART;

/**
 * 引导页
 */
public class WelAct extends RxFragmentActivity implements IView {

    private static final String TAG = WelAct.class.getSimpleName();
    @BindView(R.id.tv_versioncode)
    TextView tvVersionCode;
    @BindView(R.id.my_tc)
    TextClock mTextClock;
    @BindView(R.id.et_content)
    EditText et_content;

    @Inject
    RegistePresenter mRegistePresenter;

    @DebugLog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//注入Arouter
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

        setTextClock();
        //注册文本输入事件处理
        initEvent();
        //testARouter
        ARouter.getInstance().inject(this);
    }

    private final float ROUND_RADIUS = 6F;
    private final float[] ROUND_RECT = new float[8]; // 选中日期绘制的圆角
    void drawCircle(){
        for (int i = 0; i < 8; i++) {
            ROUND_RECT[i] = ROUND_RADIUS;
        }
    }

    private void initEvent() {
        et_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_content.addTextChangedListener(new FilterTextWatcher(et_content));
//        et_content.addTextChangedListener(new MaxLengthWatcherUtil(8,et_content));
    }

    private void setTextClock() {
        // 设置12时制显示格式
        mTextClock.setFormat12Hour("EEEE, MMMM dd, yyyy h:mmaa");
        // 设置24时制显示格式
        mTextClock.setFormat24Hour("yyyy-MM-dd hh:mm, EEEE");
    }

    @OnClick({R.id.tv_Chart,R.id.tv_toeasemob,R.id.tv_wel,R.id.tv_toTestUtils,R.id.tv_testact,R.id.tv_testARouter,R.id.tv_testARouterArgs})
    @DebugLog
    void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_Chart:
                ARouter.getInstance().build(ACTIVITY_URL_CHART).navigation();
                break;
            case R.id.tv_testARouter:
                ARouter.getInstance().build(ACTIVITY_URL_AROUTER).navigation();
                break;
            case R.id.tv_testARouterArgs://路由带参数跳转
                ARouter.getInstance()
                        .build(ACTIVITY_URL_AROUTER)
//                        .build(Uri.parse(ACTIVITY_URL_AROUTER))//使用Uri跳转
                        .withString("name","snow")
                        .withParcelable("aRouterBean",new ARouterBean("1","xlee"))
//                        .withTransition()//页面跳转动画
                        .navigation();
                break;
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
