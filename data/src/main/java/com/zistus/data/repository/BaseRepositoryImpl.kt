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

}