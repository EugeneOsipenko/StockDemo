package com.eugeneosipenko.stockdemo.network

import com.eugeneosipenko.stockdemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url()
        val url = httpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .build()

        val newBuilder = original.newBuilder()
            .url(url)

        return chain.proceed(newBuilder.build())
    }
}
