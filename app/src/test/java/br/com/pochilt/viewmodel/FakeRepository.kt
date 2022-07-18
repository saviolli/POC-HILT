package br.com.pochilt.viewmodel

import br.com.pochilt.di.RepositoryModule
import br.com.pochilt.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeRepository {

    @Singleton
    @Binds
    fun provideFakeRepository(): UserRepository = FakeRepositoryImpl()
}