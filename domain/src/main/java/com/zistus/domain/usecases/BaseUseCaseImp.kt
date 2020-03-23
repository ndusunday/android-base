package com.zistus.domain.usecases

import com.zistus.domain.repository.BaseRepository

class BaseUseCaseImp(private val baseRepository: BaseRepository): BaseUseCase {
    override fun testString(): String = "Test"
}