package com.example.trendinggitrepositories.di

import com.example.trendinggitrepositories.data.remote.RemoteDataSource
import com.example.trendinggitrepositories.data.remote.RepositoriesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModuleBind {

    @Binds
    abstract fun bindRemoteDataSource(repositoriesRemoteDataSource: RepositoriesRemoteDataSource): RemoteDataSource
}