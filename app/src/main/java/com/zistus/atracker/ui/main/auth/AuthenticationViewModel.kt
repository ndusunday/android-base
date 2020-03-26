package com.zistus.atracker.ui.main.auth

import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.atracker.extension.applyNewScheduler
import com.zistus.atracker.ui.BaseViewModel
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import com.zistus.domain.usecases.user.UserUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthenticationViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {
    val light: MutableLiveData<String> = MutableLiveData()
    val loginResponse: MutableLiveData<DataState<Entity.User>> = MutableLiveData()
    val systemError: MutableLiveData<Throwable> = MutableLiveData()
    val verificationId: MutableLiveData<String> = MutableLiveData()
    val requestCode: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var verificationCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks;

    fun testSetup() {
        userUseCase.testString()
            .applyNewScheduler()
            .subscribe { succ -> light.postValue(succ) }.track()
    }

    fun loginUser(userId: String, password: String) {
        userUseCase.loginUser(userId, password)
            .applyNewScheduler()
            .subscribe { dataState, error ->
                dataState?.let { loginResponse.postValue(dataState) }
                error?.let { systemError.postValue(it) }
            }.track()
    }

    //    fun loginUserPhone(activity: Activity, phoneNumber: String) {
//        userUseCase.loginUserPhone(activity, phoneNumber)
//            .applyNewScheduler()
//            .subscribe { dataState , error->
//                dataState?.let{ loginResponse.postValue(dataState) }
//                error?.let { systemError.postValue(it) }
//            }.track()
//    }
    fun loginUserPhone(phoneNumber: String) {
        userUseCase.verifyPhone(phoneNumber, setVerificationCallback())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .doOnComplete {
                Timber.e("Verification Initiated for $phoneNumber")
            }
            .subscribe().track()
    }

    fun loginWithCredential(code: String?) {
        verificationId.value?.let { verId->
        val credential = PhoneAuthProvider.getCredential(verId, code ?: "")
        userUseCase.verifyWithCredential(credential)
            .applyNewScheduler()
            .subscribe { dataState ->
                dataState?.let { loginResponse.postValue(dataState) }
            }.track()
        }?:kotlin.run {
            Timber.e("Invalid verification Id")
        }
    }

    fun loginWithCredential(credential: PhoneAuthCredential) {
        userUseCase.verifyWithCredential(credential)
            .applyNewScheduler()
            .subscribe { dataState ->
                dataState?.let { loginResponse.postValue(dataState) }
            }.track()
    }

    private fun setVerificationCallback(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                Timber.e("onCodeAutoRetrievalTimeOut Timeout")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId.postValue(p0)
                Timber.d("Force resending token: $p1 and verificationId: $p0")
                requestCode.postValue(true)
            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Timber.e("Verification Completed")
//                loginWithCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Timber.e("Verification Failed ${p0.message}")
            }

        }
    }
}