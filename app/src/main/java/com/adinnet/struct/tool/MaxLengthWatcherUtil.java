package com.adinnet.struct.tool;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/* 
 * 监听输入内容是否超出最大长度，并设置光标位置 
 */
public class MaxLengthWatcherUtil implements TextWatcher {
  
    private int maxLen = 0,currentLen;
    private EditText editText = null;

    public interface NumLimitCallBack {
        void num(String num, int canInputNum);
    }

    private NumLimitCallBack numCallBack ;

    public void setNumCallBack(NumLimitCallBack numCallBack) {
        this.numCallBack = numCallBack;
    }

    public MaxLengthWatcherUtil(int maxLen, EditText editText) {
        this.maxLen = maxLen;  
        this.editText = editText;  
    }  
  
    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub  
//        numCallBack.num("还可输入"+ (maxLen-currentLen)+"字",maxLen-currentLen);
    }  
  
    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        // TODO Auto-generated method stub  
    }
  
    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub  
        Editable editable = editText.getText();
        currentLen = editable.length();
        if(currentLen > maxLen)
        {  
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            boolean isContainsDot = str.contains(".");//是否包含点
            if (isContainsDot)maxLen = 7;
            else maxLen = 4;
            //截取新字符串  
            String newStr = str.substring(0,str.length()>maxLen?maxLen:str.length());
            editText.setText(newStr);  
            editable = editText.getText();
            //新字符串的长度  
            int newLen = editable.length();  
            //旧光标位置超过字符串长度  
            if(selEndIndex > newLen)  
            {  
                selEndIndex = editable.length();  
            }  
            //设置新光标所在的位置  
            Selection.setSelection(editable, selEndIndex);
        }
    }

  
}  