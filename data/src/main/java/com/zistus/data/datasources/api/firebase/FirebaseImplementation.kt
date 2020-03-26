package com.zistus.data.datasources.api.firebase

import android.app.Activity
import android.content.Context
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.zistus.data.mapper.map
import com.zistus.domain.entity.Entity
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class FirebaseImplementation(private val firebase: FirebaseAuth, val context: Context) : FirebaseInteractor {
    override fun signInEmailPassword(email: String, password: String): Single<Entity.User> {
        return Single.create<Entity.User> { emitter ->
            firebase.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    task.result?.user?.let { user ->
                        emitter.onSuccess(user.map()).also { Timber.d("Firebase user: ${user.email}") }
                    } ?: kotlin.run {
                        task.exception?.let {
                            Timber.e("Firebase error: ${it.message}")
                            emitter.tryOnError(it)
                        }
                    }
                }
        }
    }

    override fun signInWithPhone(activity: Activity, phoneNumber: String): Single<Entity.User> {
        firebase.firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, "1234")
        return Single.create { emitter ->
            /*val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    firebase.signInWithCredential(p0).addOnCompleteListener { task ->
                        task.result?.user?.let { user ->
                            emitter.onSuccess(user.map()).also { Timber.d("Firebase user: ${user.email}") }
                        } ?: kotlin.run {
                            task.exception?.let {
                                Timber.e("Firebase error: ${it.message}")
                                emitter.tryOnError(it)
                            }
                        }
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Timber.e("Firebase error: ${p0.message}")
                    emitter.tryOnError(p0)
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    Timber.e("onCodeSent:$verificationId")

                    // Save verification ID and resending token so we can use them later
//                    storedVerificationId = verificationId
//                    resendToken = token

                    // ...
                }

            }*/
            /*PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(phoneNumber, 120, TimeUnit.SECONDS, activity, mCallbacks)
//                .verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, Executors.newSingleThreadExecutor(), mCallbacks)
                .also { Timber.e("Firebase Phone Verification: $it") }*/
        }
    }

    override fun verifyPhone(phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks): Completable {
        return Completable.fromAction {
            PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(phoneNumber, 120, TimeUnit.SECONDS, Executors.newSingleThreadExecutor(), callbacks)
                .also { Timber.e("Firebase Phone Verification: $it") }
        }
    }

    override fun verifyWithCredential(credential: PhoneAuthCredential): Single<Entity.User> {
        return Single.create<Entity.User> { emitter ->
            firebase.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        task.result?.user?.let { user ->
                            emitter.onSuccess(user.map()).also { Timber.d("Firebase user: ${user.phoneNumber}") }
                        }
                    }else {
                        task.exception?.let {
                            Timber.e("Firebase error: ${it.message}")
                            emitter.tryOnError(it)
                        }?:kotlin.run {
                            val msg = "Unknown fireBase error"
                            Timber.e(msg)
                            emitter.tryOnError(Throwable(msg))
                        }
                    }
                }
        }
    }

}