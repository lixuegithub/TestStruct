package com.adinnet.struct.testutils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.adinnet.struct.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ms.Li on 2018/5/3.
 */

public class TestUtilAct extends Activity {

    @BindView(R.id.stringOrSpannedString)
    TextView stringOrSpannedString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testutils);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.stringOrSpannedString})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.stringOrSpannedString:
                stringOrSpannedString.setText(TextUtils.stringOrSpannedString(stringOrSpannedString.getText().toString() + "test1"));
                break;
            default:
                break;
        }
    }
}
