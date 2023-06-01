package com.example.sprint16_architecture.di

import android.content.Context
import com.example.sprint16_architecture.data.network.IMDbApiService
import com.example.sprint16_architecture.data.shared_pref.LocalStorage
import com.example.sprint16_architecture.data.network.NetworkClient
import com.example.sprint16_architecture.data.network.RetrofitNetworkClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IMDbApiService> {
        val imdbBaseUrl = "https://imdb-api.com"
        
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        
        Retrofit.Builder()
                .baseUrl(imdbBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(IMDbApiService::class.java)
    }
    
    single {
        androidContext()
                .getSharedPreferences("local_storage", Context.MODE_PRIVATE )
    }
    
    
    single{ LocalStorage(get()) }
    
    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }
}