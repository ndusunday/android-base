package com.zistus.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.zistus.domain.entity.Entity

fun FirebaseUser.map() = Entity.User(
    userId = this.email?:"",
    otherInfo = this.displayName?:""
)