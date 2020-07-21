package com.urban.androidhomework.api;

import android.content.Context;

import com.urban.androidhomework.BuildConfig;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static NetworkClient instance;
    private NetworkApi service;
    private Retrofit retrofit;
    private String baseUrl = "https://rickandmortyapi.com/api/";

    public static synchronized NetworkClient get() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public NetworkApi setup(Context context) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        return service = retrofit.create(NetworkApi.class);
    }

    public NetworkApi getService() {
        return service;
    }
}
