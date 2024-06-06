package com.rachel.local.di

import android.content.Context
import androidx.room.Room
import com.rachel.local.NutrientsDatabase
import com.rachel.local.dao.CalorieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): NutrientsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = NutrientsDatabase::class.java,
            name = NutrientsDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun providesCalorieDao(database: NutrientsDatabase): CalorieDao = database.calorieDao()

}
