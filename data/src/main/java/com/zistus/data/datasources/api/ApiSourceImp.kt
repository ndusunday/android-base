package com.zistus.data.datasources.api

import com.zistus.data.datasources.api.model.Dto
import com.zistus.domain.entity.Entity
import io.reactivex.Single
import retrofit2.Call

class ApiSourceImp(private val apiService: ApiService) : ApiSource {

    override fun getTestObject(): Single<Entity.TestObject> {
//        return apiService.fetchTestObject()
//            .map { item -> Entity.TestObject("23") }
        return Single.just(Entity.TestObject("01"))
    }

    override suspend fun getObjectWithCoroutine(): Call<Dto.TestObject> {
        return apiService.fetchObjectWithCoroutine()
    }
}