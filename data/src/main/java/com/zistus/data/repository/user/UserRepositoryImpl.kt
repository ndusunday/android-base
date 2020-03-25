package com.zistus.data.repository.user

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.data.datasources.api.ApiService
import com.zistus.data.datasources.api.firebase.FirebaseInteractor
import com.zistus.data.datasources.db.room.DatabaseSource
import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.user.UserRepository
import com.zistus.domain.state.DataState
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImpl(private val firebaseInteractor: FirebaseInteractor, private val apiService: ApiService, private val databaseSource: DatabaseSource): UserRepository {
    override fun loginWithEmailPassword(email: String, password: String): Single<DataState<Entity.User>> {
        return firebaseInteractor.signInEmailPassword(email, password)
            .map { item-> DataState.Success(item) as DataState<Entity.User> }
            .onErrorReturn { DataState.Failed(it, null) }
    }

    override fun loginWithPhone(activity: Activity, phoneNumber: String): Single<DataState<Entity.User>> {
        return firebaseInteractor.signInWithPhone(activity, phoneNumber)
            .map { item-> DataState.Success(item) as DataState<Entity.User> }
            .onErrorReturn { DataState.Failed(it, null) }
    }

    override fun verifyPhone(phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks): Completable {
        return firebaseInteractor.verifyPhone(phoneNumber, callbacks)
    }

    override fun verifyWithCredential(credential: PhoneAuthCredential): Single<DataState<Entity.User>> {
        return firebaseInteractor.verifyWithCredential(credential)
            .map { item -> DataState.Success(item) as DataState<Entity.User> }
            .onErrorReturn { DataState.Failed(it, null) }
    }
}