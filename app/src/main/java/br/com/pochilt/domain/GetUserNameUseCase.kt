package br.com.pochilt.domain

import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun getUserName() = userRepository.getUserName()
}