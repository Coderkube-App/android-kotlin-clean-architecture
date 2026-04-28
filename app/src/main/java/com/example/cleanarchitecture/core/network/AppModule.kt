package com.example.cleanarchitecture.core.network

import com.example.cleanarchitecture.data.remote.api.ApiService
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Centralize the base URL so Retrofit and tests can swap it from one place later.
    @Provides
    fun provideBaseUrl() = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // TODO: Reduce logging level or gate it behind a debug flag before shipping production builds.
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .cache(null)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(baseUrl: String, client: OkHttpClient): ApiService {
        // Keep Retrofit configuration in DI so every data source uses the same converters and client.
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}
