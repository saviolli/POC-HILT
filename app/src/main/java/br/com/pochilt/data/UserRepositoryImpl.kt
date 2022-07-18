package br.com.pochilt.data

import br.com.pochilt.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun getUserName() : String {
        return "Adriano"
    }
}