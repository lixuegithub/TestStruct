package com.adinnet.struct.ui.act.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.adinnet.struct.R;
import com.adinnet.struct.base.BaseApp;
import com.adinnet.struct.bean.BookItemBean;
import com.adinnet.struct.bean.BookListBean;
import com.adinnet.struct.comm.LogUtil;
import com.adinnet.struct.comm.ToastUtil;
import com.adinnet.struct.dragger.module.BookListModule;
import com.adinnet.struct.mvp.IView;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 教材列表（litePal数据存储操作）
 */
public class BookListAct extends RxFragmentActivity implements IView {
    @Inject
    BookListPresenter mBookPresenter;

    /**服务器接口数据集合*/
    BookListBean mList = new BookListBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        ButterKnife.bind(this);
        //注册Module
        BaseApp.getContext().getAppComponet().bookList(new BookListModule(this)).in(this);
    }

    @OnClick({R.id.tv_queryAll, R.id.tv_save, R.id.tv_del})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_queryAll:
                mBookPresenter.bookList();
                break;
            case R.id.tv_save:
                //TODO 测试保存一条数据
                saveOneBook();
                break;
            case R.id.tv_del:
                delOneBook();
                break;
            default:
                break;
        }
    }

    private void delOneBook() {

    }

    private void saveOneBook() {
        if(mList == null){
            ToastUtil.showShortToast("数据集为空,请先查询数据....");
            return;
        }
        BookItemBean bookItemBean = mList.getEn().get(0);
        ArrayList<BookItemBean> firstList = new ArrayList<>();
        firstList.add(bookItemBean);
        DataSupport.saveAll(firstList);
    }

    @Override
    public void startRequest() {
        LogUtil.e("startRequest...");
    }

    @Override
    public void requestResult(Object o) {
        if (o == null) {
            return;
        }
        mList = (BookListBean) o;
        LogUtil.e("BookList..cn..." + mList.getCn().size() + "....en..." + mList.getEn().size());
    }

    @Override
    public void completeRequest() {
        LogUtil.e("completeRequest...");
    }
}
