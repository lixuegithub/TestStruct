package com.adinnet.struct.dragger.componet;

import com.adinnet.struct.dragger.module.RecordPhoneModule;
import com.adinnet.struct.ui.act.record.RecordAct;

import dagger.Subcomponent;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Subcomponent(modules = RecordPhoneModule.class)
public interface RecordPhoneActComponet {
    RecordAct in(RecordAct act);
}
