package com.adinnet.struct.dragger.componet;

import com.adinnet.struct.dragger.module.BookListModule;
import com.adinnet.struct.ui.act.book.BookListAct;

import dagger.Subcomponent;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Subcomponent(modules = BookListModule.class)
public interface BookListActComponet {
    BookListAct in(BookListAct act);
}
