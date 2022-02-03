package com.example.playground.di

import com.example.playground.repository.MainRepository
import com.example.playground.retrofit.BlogRetrofit
import com.example.playground.retrofit.NetworkMapper
import com.example.playground.room.BlogDao
import com.example.playground.room.CacheMapper
import com.example.playground.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit
    ): MainRepository {
        return MainRepository(
            networkMapper,
            cacheMapper,
            blogRetrofit,
            blogDao
        )
    }
}