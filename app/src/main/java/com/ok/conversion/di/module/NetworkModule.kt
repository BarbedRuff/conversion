package com.ok.conversion.di.module

import android.net.Uri
import com.ok.conversion.data.api.ApiService
import com.ok.conversion.data.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideRetrofitModule(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitHelper.BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val url = originalRequest.url()
                val newUrl = Uri.Builder()
                    .scheme(url.scheme())
                    .authority(url.host())
                    .appendPath("v6")
                    .appendPath(RetrofitHelper.API_KEY)
                    .appendEncodedPath(url.encodedPath().substring(1))
                    .build().toString()
                val newRequest = originalRequest.newBuilder().url(newUrl).build()
                chain.proceed(newRequest)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}