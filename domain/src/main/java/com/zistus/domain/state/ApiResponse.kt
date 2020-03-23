package com.zistus.domain.state

class ApiResponse<T> (
    val response: T?,
    val error: Throwable?,
    val message: String
)