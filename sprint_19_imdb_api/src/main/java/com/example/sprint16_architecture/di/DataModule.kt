package com.example.sprint16_architecture.di

import android.content.Context
import com.example.sprint16_architecture.core.data.NamesRepositoryImpl
import com.example.sprint16_architecture.core.data.converters.MovieCastConverter
import com.example.sprint16_architecture.core.data.network.IMDbApiService
import com.example.sprint16_architecture.core.data.network.NetworkClient
import com.example.sprint16_architecture.core.data.network.RetrofitNetworkClient
import com.example.sprint16_architecture.core.data.shared_pref.LocalStorage
import com.example.sprint16_architecture.core.domain.api.MoviesRepository
import com.example.sprint16_architecture.core.domain.api.NamesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://imdb-api.com"

val dataModule = module {
    single<IMDbApiService> {
        
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(IMDbApiService::class.java)
    }
    
    single { androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE ) }

    single{ LocalStorage(get()) }
    
    single<NetworkClient> { RetrofitNetworkClient(get(), androidContext()) }

    single<MoviesRepository> {
        com.example.sprint16_architecture.core.data.MoviesRepositoryImpl(
            networkClient = get(),
            localStorage = get(),
            movieCastConverter = get()
        )
    }

    single { MovieCastConverter() }
    single<NamesRepository> { NamesRepositoryImpl(networkClient = get()) }
}