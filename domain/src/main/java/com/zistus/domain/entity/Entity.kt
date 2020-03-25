package com.zistus.domain.entity

sealed class Entity {
    data class TestObject(val id: String)
    data class User(
        val userId: String,
        val otherInfo: String
    ): Entity()

    data class VerifiedPhone(
        val userId: String,
        val token: String
    ): Entity()
}