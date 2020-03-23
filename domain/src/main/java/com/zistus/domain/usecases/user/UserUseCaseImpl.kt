package com.zistus.domain.usecases.user

import com.zistus.domain.repository.user.UserRepository
import io.reactivex.Single

class UserUseCaseImpl(val userRepository: UserRepository): UserUseCase {
    override fun testString(): Single<String> = Single.just("This is a test string")
}