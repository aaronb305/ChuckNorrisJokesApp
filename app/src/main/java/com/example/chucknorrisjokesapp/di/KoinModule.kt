package com.example.chucknorrisjokesapp.di

import com.example.chucknorrisjokesapp.rest.ChuckNorrisApi
import com.example.chucknorrisjokesapp.rest.ChuckNorrisApiRepository
import com.example.chucknorrisjokesapp.rest.ChuckNorrisApiRepositoryImpl
import com.example.chucknorrisjokesapp.viewmodel.ChuckNorrisViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesChuckNorrisApi(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(ChuckNorrisApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ChuckNorrisApi::class.java)

    fun providesChuckNorrisRepository(chuckNorrisApi: ChuckNorrisApi) : ChuckNorrisApiRepository =
        ChuckNorrisApiRepositoryImpl(chuckNorrisApi)

    single { providesLoggingInterceptor() }
    single { providesOkHttpClient(get()) }
    single { providesChuckNorrisApi(get()) }
    single { providesChuckNorrisRepository(get()) }
}

val viewModelModule = module {

    fun providesDispatcher() : CoroutineDispatcher = Dispatchers.IO

    single { providesDispatcher() }
    viewModel { ChuckNorrisViewModel(get(), get()) }
}