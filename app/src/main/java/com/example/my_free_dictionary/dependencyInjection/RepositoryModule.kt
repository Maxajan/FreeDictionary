package com.example.my_free_dictionary.dependencyInjection
import com.example.my_free_dictionary.data.ImplementRepository
import com.example.my_free_dictionary.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)

abstract class RepositoryModule {
    @Singleton
    @Binds

    abstract fun bindRepository(implement: ImplementRepository): Repository

}