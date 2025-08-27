package com.example.my_free_dictionary.dependencyInjection

import com.example.my_free_dictionary.network.ApiDictionary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)

object NetworkModule {
@Singleton
@Provides
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://freedictionaryapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

    @Singleton
    @Provides
    fun provideApi (retrofit: Retrofit): ApiDictionary{
        return retrofit.create(ApiDictionary::class.java)
    }

}