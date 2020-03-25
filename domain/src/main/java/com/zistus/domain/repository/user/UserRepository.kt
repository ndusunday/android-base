package com.zistus.domain.repository.user

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.BaseRepository
import com.zistus.domain.state.DataState
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository: BaseRepository {
    fun loginWithEmailPassword(email: String, password: String): Single<DataState<Entity.User>>
    fun loginWithPhone(activity: Activity, phoneNumber: String): Single<DataState<Entity.User>>
    fun verifyPhone(phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks): Completable
    fun verifyWithCredential(credential: PhoneAuthCredential): Single<DataState<Entity.User>>
}