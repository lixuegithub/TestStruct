package com.adinnet.struct.easemob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adinnet.struct.R;
import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.comm.ToastUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMCheckType;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 环信聊天测试
 */
public class EaseMobAct extends Activity {

    @BindView(R.id.et_service_check)
    EditText serviceCheckResultView;
    String registerName="xlee2020";
    String registerPwd="123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_easemob);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_moblogin,R.id.tv_mobregiste,R.id.tv_checkServer})
    void OnClickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_mobregiste:
                registeMob();
                break;
            case R.id.tv_moblogin:
                loginMob();
                break;
            case R.id.tv_checkServer:
                easeMobCheck();
                break;
            default:
                break;
        }
    }

    /**注册环信(注册方法是同步的，需开启线程执行)*/
    private void registeMob() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**注册失败会抛出HyphenateException*/
                try {
                    EMClient.getInstance().createAccount(registerName, registerPwd);
                    ToastUtil.showShortToast("注册成功....username.."+registerName);
                }catch (HyphenateException e){
                    e.printStackTrace();
                    LogUtil.e("注册失败..e.."+e.toString());
                }
            }
        }).start();
    }

    /**登录环信*/
    private void loginMob() {
        EMClient.getInstance().login(registerName, registerPwd,new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Toast.makeText(EaseMobAct.this,"登录聊天服务器成功！",Toast.LENGTH_SHORT).show();
                LogUtil.e("main", "登录聊天服务器成功！");
                startActivity(new Intent(EaseMobAct.this,EaseMobMsgAct.class));
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                LogUtil.e("main", "登录聊天服务器失败！"+"==code==="+code+"===msg=="+message);
            }
        });
    }

    /**服务诊断*/
    private void easeMobCheck() {
        final StringBuilder builder = new StringBuilder();
        EMClient.getInstance().check(registerName, registerPwd, new EMClient.CheckResultListener() {
            @Override
            public void onResult(int type, int result, String desc) {
                switch (type) {
                    case EMCheckType.ACCOUNT_VALIDATION: // Account validation.
                        if (result != EMError.EM_NO_ERROR) {
                            updateResultOnUiThread(builder, R.string.check_result_account_validate_fail, result, desc);
                        }
                        break;
                    case EMCheckType.GET_DNS_LIST_FROM_SERVER: // Get dns list from server.
                        if (result == EMError.EM_NO_ERROR) {
                            updateResultOnUiThread(builder, R.string.check_result_get_dns_list_success, 0, null);
                        } else {
                            updateResultOnUiThread(builder, R.string.check_result_get_dns_list_fail, result, desc);
                        }
                        break;
                    case EMCheckType.GET_TOKEN_FROM_SERVER: // Get token from server.
                        if (result == EMError.EM_NO_ERROR) {
                            updateResultOnUiThread(builder, R.string.check_result_get_token_success, 0, null);
                        } else {
                            updateResultOnUiThread(builder, R.string.check_result_get_token_fail, result, desc);
                        }
                        break;
                    case EMCheckType.DO_LOGIN: // User login
                        if (result == EMError.EM_NO_ERROR) {
                            updateResultOnUiThread(builder, R.string.check_result_login_success, 0, null);
                        } else {
                            updateResultOnUiThread(builder, R.string.check_result_login_fail, result, desc);
                        }
                        break;
                    case EMCheckType.DO_LOGOUT: // User logout
                        if (result == EMError.EM_NO_ERROR) {
                            updateResultOnUiThread(builder, R.string.check_result_logout_success, 0, null);
                        } else {
                            updateResultOnUiThread(builder, R.string.check_result_logout_fail, result, desc);
                        }
                        break;
                }
            }
        });
    }

    private void updateResultOnUiThread(final StringBuilder builder, @StringRes int resId, int result, String desc) {
        builder.append(String.format(getString(resId), result == EMError.EM_NO_ERROR ? "" : ", error code: " + result,
                TextUtils.isEmpty(desc) ? "" : ", desc: " + desc))
                .append("\n");
        showResultOnUiThread(builder.toString());
    }

    private void showResultOnUiThread(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {return;}
                serviceCheckResultView.setText(result);
            }
        });
    }


}
