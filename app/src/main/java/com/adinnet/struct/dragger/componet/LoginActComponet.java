package com.adinnet.struct.dragger.componet;

import com.adinnet.struct.dragger.module.LoginModule;
import com.adinnet.struct.ui.act.login.LoginAct;

import dagger.Subcomponent;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Subcomponent(modules = LoginModule.class)
public interface LoginActComponet {
    LoginAct in(LoginAct act);
}
