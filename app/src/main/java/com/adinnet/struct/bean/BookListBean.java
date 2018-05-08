package com.adinnet.struct.bean;

import java.util.List;

public class BookListBean {

    private List<BookItemBean> cn;
    private List<BookItemBean> en;

    public List<BookItemBean> getCn() {
        return cn;
    }

    public void setCn(List<BookItemBean> cn) {
        this.cn = cn;
    }

    public List<BookItemBean> getEn() {
        return en;
    }

    public void setEn(List<BookItemBean> en) {
        this.en = en;
    }

}
