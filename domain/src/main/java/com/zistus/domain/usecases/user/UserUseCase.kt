package com.zistus.domain.usecases.user

import io.reactivex.Single

interface UserUseCase {
    fun testString(): Single<String>
}