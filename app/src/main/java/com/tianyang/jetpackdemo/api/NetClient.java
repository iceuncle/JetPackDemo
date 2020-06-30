package com.tianyang.jetpackdemo.api;

import com.tianyang.jetpackdemo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetClient {
    private static volatile NetClient instance;

    private final long CONNECT_TIME_OUT = 5L;
    private final long READ_TIME_OUT = 5L;
    private final long WRITE_TIME_OUT = 5L;


    public static NetClient getInstance() {
        if (instance == null) {
            synchronized (NetClient.class) {
                if (instance == null) {
                    instance = new NetClient();
                }
            }
        }
        return instance;
    }

    private Retrofit getRetrofit(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> service) {
        return create("https://www.wanandroid.com", service);
    }

    public static <T> T create(String baseUrl, Class<T> service) {
        return getInstance().getRetrofit(baseUrl).create(service);
    }

}
