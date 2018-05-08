package com.adinnet.struct.dragger.componet;

import com.adinnet.struct.dragger.module.RegisteModule;
import com.adinnet.struct.ui.act.wel.WelAct;

import dagger.Subcomponent;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Subcomponent(modules = RegisteModule.class)
public interface RegisteActComponet {
    WelAct in(WelAct act);
}
