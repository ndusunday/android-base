package com.zistus.data.mapper

import com.zistus.data.datasources.api.model.Dto
import com.zistus.domain.entity.Entity

fun Dto.TestObject.toEntity() = Entity.TestObject(id = id)