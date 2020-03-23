package com.zistus.data.repository

import com.zistus.data.datasources.api.ApiSource
import com.zistus.data.datasources.api.model.Dto
import com.zistus.data.datasources.db.room.DatabaseSource
import com.zistus.data.mapper.toEntity
import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.BaseRepository
import com.zistus.domain.state.ApiResponse
import com.zistus.domain.state.DataState
import io.reactivex.Single

class BaseRepositoryImpl(private val apiSource: ApiSource, private val databaseSource: DatabaseSource) :
    BaseRepository {

    override fun fetchTestObject(): Single<Entity.TestObject> {
        return apiSource.getTestObject()
            .map { item -> item }
            .onErrorReturn { Entity.TestObject("30949494") }
    }

    override fun persistTestObject(item: Entity.TestObject): Long {
        return databaseSource.insertItem(item = item)
    }
    override suspend fun <T : Any> fetchWithCoroutine(): DataState<Entity.TestObject> {
        val response = apiSource.getObjectWithCoroutine().execute()
        val body = response.body()
        return body?.let {
            DataState.Success(body.toEntity())
        } ?: kotlin.run {
            DataState.Failed(Throwable(response.errorBody()?.string().toString()), null)
        }
    }

    override fun fetchTestObjectWithDataState(): Single<DataState<Entity.TestObject>> {
        return apiSource.getTestObject()
            .map { item -> DataState.Success(item) as DataState<Entity.TestObject> }
            .onErrorReturn { error ->  DataState.Failed(error, null) }
    }

}