package com.adinnet.struct.dragger.componet;

import com.adinnet.struct.dragger.module.ApiModule;
import com.adinnet.struct.dragger.module.AppModule;
import com.adinnet.struct.dragger.module.BookListModule;
import com.adinnet.struct.dragger.module.LoginModule;
import com.adinnet.struct.dragger.module.RecordPhoneModule;
import com.adinnet.struct.dragger.module.RegisteModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component就是注入者与被注入者之间联系的桥梁
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponet {

    LoginActComponet Login(LoginModule loginModule);

    RegisteActComponet registe(RegisteModule registeModule);

    RecordPhoneActComponet record(RecordPhoneModule recordPhoneModule);

    BookListActComponet bookList(BookListModule bookListModule);

}
