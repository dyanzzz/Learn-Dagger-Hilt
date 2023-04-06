package com.example.learnhilt.di.module

import com.example.learnhilt.BuildConfig
import com.example.learnhilt.data.api.ApiService
import com.example.learnhilt.ui.main.domain.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Named("BASE_URL")
    fun provideWebAPI(): String = BuildConfig.BASE_URL

    /*@Provides
    @Named("KEY_API")
    fun provideKeyAPI(): String = KEY_API

    @Provides
    @Named("PAGE_SIZE")
    fun providePageSize(): Int = PAGE_SIZE*/

    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") webAPI: String,
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(serviceHTTPClient())
            .addInterceptor(httpLoggingInterceptor())
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun serviceHTTPClient(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestUrl = original
                .url
                .newBuilder()
                .build()

            val requestBuilder = original.newBuilder().url(requestUrl)
                .build()
            return@Interceptor chain.proceed(requestBuilder)
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun provideGamesService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    /*@Provides
    fun provideGamesRepository(
        gamesService: ApiService,
        @Named("KEY_API") keyApi: String,
        @Named("PAGE_SIZE") pageSize: Int,
    ): GamesRepository = GamesRepositoryImpl(
        gamesService = gamesService,
        pageSize = pageSize,
        apiKey = keyApi
    )*/

    @Provides
    fun provideGamesRepository(
        apiService: ApiService,
    ): MainRepository = MainRepository(
        apiHelper = apiService,
    )
}