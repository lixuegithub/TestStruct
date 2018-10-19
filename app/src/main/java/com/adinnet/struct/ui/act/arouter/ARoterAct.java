package com.adinnet.struct.ui.act.arouter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.adinnet.struct.R;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.ButterKnife;

import static com.adinnet.struct.tool.Constants.ACTIVITY_URL_AROUTER;

/**
 * 在支持路由的页面上添加注解（必填）
 * 这里的路径需要注意的是至少需要两级，/xx/xx
 * 路径的标签个人建议写在一个类里面 方便统一管理维护
 * Ps：不要忘了在清单文件里面配置Activity
 */
@Route(path = ACTIVITY_URL_AROUTER)
public class ARoterAct extends RxFragmentActivity {
    //在目标界使用Autowired注解获取对应的参数
    @Autowired()
    String name;
    @Autowired(name = "aRouterBean")
    ARouterBean aRouterBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_arouter);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        Log.e("xlee","name.."+name);
        Log.e("xlee","aRouterBean.."+aRouterBean.toString());
    }

}
