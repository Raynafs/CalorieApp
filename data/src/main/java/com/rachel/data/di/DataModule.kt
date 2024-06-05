package com.rachel.data.di

import com.rachel.data.CalorieRepository
import com.rachel.data.DefaultCalorieRepository
import com.rachel.local.dao.CalorieDao
import com.rachel.remote.CalorieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesDefaultRepository(dao: CalorieDao, api: CalorieApi): CalorieRepository =
        DefaultCalorieRepository(dao, api)

}