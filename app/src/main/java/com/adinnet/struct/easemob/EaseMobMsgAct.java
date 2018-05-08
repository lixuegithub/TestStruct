package com.adinnet.struct.easemob;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adinnet.struct.R;
import com.adinnet.struct.comm.ToastUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 环信消息发送界面
 */
public class EaseMobMsgAct extends RxFragmentActivity {

    @BindView(R.id.tv_mobContent)
    TextView tvMsgContent;
    @BindView(R.id.et_content)
    EditText etMsgContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_easemobmsg);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_sendmsg})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_sendmsg:
                if (TextUtils.isEmpty(etMsgContent.getText().toString())){
                    ToastUtil.showShortToast("请输入发送的消息..");
                    return;
                }
                easeMobSendMsg(etMsgContent.getText().toString());
                break;
            default:
                break;
        }
    }

    private void easeMobSendMsg(String content) {
        String toChatUsername ="xlee2019";
        EMMessage emMessage = EMMessage.createTxtSendMessage(content,toChatUsername);
        EMClient.getInstance().chatManager().sendMessage(emMessage);
    }

}
