package com.adinnet.struct.dragger.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ms.Li on 2018/3/23.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application providerApplication(){
        return this.application;
    }
}
