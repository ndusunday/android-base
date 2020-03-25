package com.zistus.domain.usecases.user

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.domain.entity.Entity
import com.zistus.domain.repository.user.UserRepository
import com.zistus.domain.state.DataState
import io.reactivex.Completable
import io.reactivex.Single

class UserUseCaseImpl(val userRepository: UserRepository): UserUseCase {
    override fun testString(): Single<String> = Single.just("This is a test string")

    override fun loginUser(userId: String, password: String): Single<DataState<Entity.User>> {
        return userRepository.loginWithEmailPassword(userId, password)
    }

    override fun loginUserPhone(activity: Activity, phoneNumber: String): Single<DataState<Entity.User>> {
        return userRepository.loginWithPhone(activity, phoneNumber)
    }

    override fun verifyPhone(phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks): Completable {
        return userRepository.verifyPhone(phoneNumber, callbacks)
    }

    override fun verifyWithCredential(credential: PhoneAuthCredential): Single<DataState<Entity.User>> {
        return userRepository.verifyWithCredential(credential)
    }
}