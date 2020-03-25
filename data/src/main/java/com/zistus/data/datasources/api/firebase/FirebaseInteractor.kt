package com.zistus.data.datasources.api.firebase

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.domain.entity.Entity
import io.reactivex.Completable
import io.reactivex.Single

interface FirebaseInteractor {
    fun signInEmailPassword(email: String, password: String): Single<Entity.User>
    fun signInWithPhone(activity: Activity, phoneNumber: String): Single<Entity.User>
    fun verifyPhone(phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks): Completable
    fun verifyWithCredential(credential: PhoneAuthCredential): Single<Entity.User>
}