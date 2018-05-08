package com.adinnet.struct.http;

import com.adinnet.struct.bean.BookListBean;
import com.adinnet.struct.bean.RegisterBean;
import com.adinnet.struct.modle.BaseResponse;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/** 网络请求Api*/
public interface Api {

    @GET("api/v1/salesClues/regSalesClues")
    Observable<BaseResponse<Object>> register(@Query("source") int source, @Query("userName") String userName, @Query("telNo") String telNo, @Query("goodsId") String goodsId);

    @GET("tv/registerDevice")
    Observable<BaseResponse<RegisterBean>> registe();

    @GET("tv/textbookList")
    Observable<BaseResponse<BookListBean>> bookList();

    @POST("login")
    Observable<BaseResponse<Object>> login(@Field("mobile") String mobile, @Field("password") String password, @Field("devicecode") String devicecode, @Field("devicetype") String devicetype);

    @POST("api/v1/salesClues/regSalesClues")
    Observable<BaseResponse<Object>> recordBuyPhone(@Query("source") int source, @Query("userName") String userName, @Query("telNo") String telNo, @Query("goodsId") String goodsId);
}
