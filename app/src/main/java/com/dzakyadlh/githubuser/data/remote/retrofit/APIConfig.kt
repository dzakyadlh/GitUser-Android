package com.dzakyadlh.githubuser.data.remote.retrofit

import com.dzakyadlh.githubuser.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIConfig {
    companion object {
        fun getAPIService(): APIService {
            val AuthKey = BuildConfig.KEY
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", AuthKey)
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder().addInterceptor(authInterceptor).build()
            val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(APIService::class.java)
        }
    }
}