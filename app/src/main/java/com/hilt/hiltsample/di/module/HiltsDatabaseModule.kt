package com.hilt.hiltsample.di.module

import android.app.Application
import com.hilt.hiltsample.data.local.HiltSampleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class HiltsDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = HiltSampleDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: HiltSampleDatabase) = database.getPostsDao()
}