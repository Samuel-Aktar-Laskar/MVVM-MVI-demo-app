package com.example.playground.repository

import com.example.playground.model.Blog
import com.example.playground.retrofit.BlogRetrofit
import com.example.playground.retrofit.NetworkMapper
import com.example.playground.room.BlogDao
import com.example.playground.room.CacheMapper
import com.example.playground.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper,
    private val blogRetrofit: BlogRetrofit,
    private val blogDao: BlogDao
){
    suspend fun getBlogs(): Flow<DataState<List<Blog>>>  = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(
                    cacheMapper.mapToEntity(blog)
                )
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }

    }
}