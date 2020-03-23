package com.zistus.data.datasources.api

import com.zistus.data.datasources.api.model.Dto
import com.zistus.domain.entity.Entity
import io.reactivex.Single
import retrofit2.Call

interface ApiSource {
    fun getTestObject(): Single<Entity.TestObject>
    suspend fun getObjectWithCoroutine(): Call<Dto.TestObject>
}