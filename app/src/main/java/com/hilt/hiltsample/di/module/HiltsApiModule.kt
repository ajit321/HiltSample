package com.hilt.hiltsample.di.module

import com.hilt.hiltsample.data.remote.api.HiltService
import com.hilt.hiltsample.model.ProductsModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import de.stefanmedack.adapter.FilterNullValuesFromListAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class HiltsApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): HiltService = Retrofit.Builder()
        .baseUrl(HiltService.HILT_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .add(KotlinJsonAdapterFactory())
                    .add(FilterNullValuesFromListAdapter.newFactory(ProductsModel::class.java))
                    .build()
            )
        )
        .build()
        .create(HiltService::class.java)
}