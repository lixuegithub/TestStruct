package com.adinnet.struct.dragger.module;

import android.app.Application;

import com.adinnet.struct.comm.Constant;
import com.adinnet.struct.http.Api;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ms.Li on 2018/3/23.
 */

@Module
public class ApiModule {

    private final static int TIME_OUT_SECONDS = 600;

    @Provides
    @Singleton
    public OkHttpClient providerClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//拦截级别
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .build();
        return client;
    }

    @Provides
    @Singleton
    public Retrofit providerRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public Api providerApiInfo(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    public ApiManager providerApiManager(Application application, Api api){
        return new ApiManager(application,api);
    }

}
