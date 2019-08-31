package com.example.aplikasigithub

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun httpClient() : OkHttpClient {
    val logInteceptor = HttpLoggingInterceptor()
    logInteceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()

    builder.readTimeout(15 , TimeUnit.SECONDS)
    builder.connectTimeout(15 , TimeUnit.SECONDS)
    builder.addInterceptor(logInteceptor)

    return builder.build()
}

inline fun <reified T> apiRequest(okHttpClient: OkHttpClient) : T {
    val gson = GsonBuilder().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    return retrofit.create(T::class.java)
}