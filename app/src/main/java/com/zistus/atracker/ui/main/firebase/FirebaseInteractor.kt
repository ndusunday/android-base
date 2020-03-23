package com.zistus.atracker.ui.main.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.zistus.domain.state.DataState
import io.reactivex.Single

interface FirebaseInteractor {

//    suspend fun <T : Any> signInUser(user: String, password: String): DataState<FirebaseUser>

    suspend fun signInHandler(user: String, password: String): Task<AuthResult>

    fun signInUserObservable(user: String, password: String): Single<FirebaseUser>?
}