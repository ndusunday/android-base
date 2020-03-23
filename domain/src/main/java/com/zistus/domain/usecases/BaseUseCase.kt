package com.zistus.domain.usecases

import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import io.reactivex.Single

interface BaseUseCase {
    fun getTestObject(): Single<Entity.TestObject>
    suspend fun fetchTestWithCoroutine(): DataState<Entity.TestObject>
    fun fetchTestObjectDataState(): Single<DataState<Entity.TestObject>>
}