package com.zistus.domain.repository

import com.zistus.domain.entity.Entity
import com.zistus.domain.state.ApiResponse
import com.zistus.domain.state.DataState
import io.reactivex.Single


interface BaseRepository {
    fun fetchTestObject(): Single<Entity.TestObject>

    fun fetchTestObjectWithDataState(): Single<DataState<Entity.TestObject>>

    fun persistTestObject(item: Entity.TestObject): Long

    suspend fun<T: Any> fetchWithCoroutine(): DataState<Entity.TestObject>

}