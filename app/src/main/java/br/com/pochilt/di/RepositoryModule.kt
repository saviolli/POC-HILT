package br.com.pochilt.di

import br.com.pochilt.data.UserRepositoryImpl
import br.com.pochilt.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()
}