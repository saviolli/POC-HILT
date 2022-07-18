package br.com.pochilt.viewmodel

import br.com.pochilt.domain.UserRepository
import javax.inject.Inject

class FakeRepositoryImpl @Inject constructor(): UserRepository {
    override fun getUserName() = "Fake User"
}