package com.adinnet.struct.tool;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/6/28 0028.
 * 输入框输入文字指定条件过滤
 */

public class FilterTextWatcher implements TextWatcher {
    /**
     * et
     */
    private EditText et = null;
    /**
     * 筛选条件
     */
    private String regex;
    /**
     * 默认的筛选条件(正则:只能输入中文)
     */
//    private String DEFAULT_REGEX = "[^\u4E00-\u9FA5]";
    private String DEFAULT_REGEX = "[^[1-9]{0,4}(\\.|0\\.)?[0-9]{0,2}$]";

    /**
     * 构造方法
     *
     * @param et
     */
    public FilterTextWatcher(EditText et) {
        this.et = et;
        this.regex = DEFAULT_REGEX;
    }

    /**
     * 构造方法
     *
     * @param et    et
     * @param regex 筛选条件
     */
    public FilterTextWatcher(EditText et, String regex) {
        this.et = et;
        this.regex = regex;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        String inputStr = clearLimitStr(regex, str);
        et.removeTextChangedListener(this);
        // et.setText方法可能会引起键盘变化,所以用editable.replace来显示内容
        editable.replace(0, editable.length(), inputStr.trim());
        et.addTextChangedListener(this);
    }

    /**
     * 清除不符合条件的内容
     *
     * @param regex
     * @return
     */
    private String clearLimitStr(String regex, String str) {
        return str.replaceAll(regex, "");
    }



}
