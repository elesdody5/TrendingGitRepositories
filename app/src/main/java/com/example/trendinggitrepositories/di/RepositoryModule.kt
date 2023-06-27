package com.example.trendinggitrepositories.di

import com.example.trendinggitrepositories.data.RepositoryGateway
import com.example.trendinggitrepositories.data.RepositoryGatewayImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun bindRepoGateway(repositoryGatewayImp: RepositoryGatewayImp): RepositoryGateway
}