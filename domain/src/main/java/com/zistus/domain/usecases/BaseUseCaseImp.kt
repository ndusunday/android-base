package com.zistus.domain.usecases

import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.BaseRepository
import com.zistus.domain.state.DataState
import io.reactivex.Single

class BaseUseCaseImp(private val baseRepository: BaseRepository): BaseUseCase {

    override fun getTestObject(): Single<Entity.TestObject> {
        return baseRepository.fetchTestObject()
    }

    override suspend fun fetchTestWithCoroutine(): DataState<Entity.TestObject> {
        return baseRepository.fetchWithCoroutine<Entity.TestObject>()
    }

    override fun fetchTestObjectDataState(): Single<DataState<Entity.TestObject>> {
        return baseRepository.fetchTestObjectWithDataState()
    }
}