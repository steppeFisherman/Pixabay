package com.example.pixabay.ui.di

import android.content.Context
import com.example.pixabay.data.net.CloudData
import com.example.pixabay.data.net.MyService
import com.example.pixabay.data.repository.*
import com.example.pixabay.domain.Repository
import com.example.pixabay.utils.SnackBuilder
import com.example.pixabay.utils.connectivity.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSnackBuilder(): SnackBuilder = SnackBuilder.Base()

    @Provides
    @Singleton
    fun provideConnectionLiveData(@ApplicationContext context: Context): ConnectionLiveData =
        ConnectionLiveData(context)

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(MyService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): MyService = retrofit.create(MyService::class.java)

    @Provides
    @Singleton
    fun provideCloud(myService: MyService): CloudData = CloudData.Base(myService)

    @Provides
    @Singleton
    fun provideMapCloudToDomain(): MapCloudToDomain = MapCloudToDomain.Base()

    @Provides
    fun provideDispatchers(): ToDispatch = ToDispatch.Base()

    @Provides
    fun provideExceptionHandle(): ExceptionHandle = ExceptionHandle.Base()

    @Provides
    fun provideCloudSource(
        mapCloudToDomain: MapCloudToDomain,
        cloudData: CloudData,
        dispatchers: ToDispatch,
    ): CloudSource = CloudSource.Base(
        mapperCloudToDomain = mapCloudToDomain,
        cloudData = cloudData,
        dispatchers = dispatchers,
    )

    @Provides
    @Singleton
    fun provideRepository(
        cloudSource: CloudSource,
        exceptionHandle: ExceptionHandle
    ): Repository = RepositoryImpl(
        cloudSource = cloudSource,
        exceptionHandle = exceptionHandle
    )
}