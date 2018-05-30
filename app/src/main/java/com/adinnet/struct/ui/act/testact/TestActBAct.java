package com.adinnet.struct.ui.act.testact;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.adinnet.struct.R;
import com.adinnet.struct.comm.LogUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ms.Li on 2018/5/3.
 */

public class TestActBAct extends Activity {

    private static final String TAG = TestActBAct.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testb);
        ButterKnife.bind(this);
        LogUtil.e(TAG+"...onCreate...");
    }

    @OnClick(R.id.tv_finishAct)
    void onClick(View view){
        finish();
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
