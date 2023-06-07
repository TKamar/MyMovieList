package com.example.mymovielist.Services;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient().newBuilder().addInterceptor(newInterceptor()).build())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

    private static Interceptor newInterceptor() {

        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original =chain.request();
                HttpUrl httpUrl = original.url().newBuilder().addQueryParameter("api_key","0ca1b003e8550baee324f2d0413eb7ac").build();
                original = original.newBuilder().url(httpUrl).build();
                return chain.proceed(original);
            }
        };

    }
}
