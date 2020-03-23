package com.zistus.atracker.ui.main.firebase

import android.annotation.SuppressLint
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.zistus.atracker.ui.main.firebase.entry.Entry
import com.zistus.atracker.ui.main.firebase.entry.UserType
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import io.reactivex.Single
import kotlinx.coroutines.yield
import timber.log.Timber
import java.lang.RuntimeException

class FirebaseImpl(): FirebaseInteractor {

    init {

    }

    fun createAUser(dbRef: DatabaseReference, user: Entry.User) {
        when (user.type) {
            UserType.ADMIN -> {
                dbRef.child("admin").child(user.email?:"").setValue(user)
            }
            UserType.DELIEVERER -> {
                dbRef.child("deliverer").child(user.email?:"").setValue(user)
            }
            else -> {
                dbRef.child("users").child(user.email?:"").setValue(user)
            }
        }

    }

    @SuppressLint("CheckResult")
    override fun signInUserObservable(user: String, password: String): Single<FirebaseUser> {
        return Single.create<FirebaseUser> { emitter ->
            //Call OnCompleteListener if the user is signed in successfully//
            FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password).addOnCompleteListener { task ->
                //If the user has been authenticated...//
                if (task.isSuccessful) {
                    task.result?.user?.let { emitter.onSuccess(it) }
                } else {
                    //If sign in fails, then log the error//
                    task.exception?.let { msg ->
                        Timber.d("Firebase authentication failed ${msg.message}")
                        emitter.tryOnError(msg)
                    }
                }
            }
        }
    }

    override suspend fun signInHandler(user: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password)

//    override suspend fun <T : Any> signInUser(user: String, password: String): DataState<FirebaseUser> {
//        var fUser: FirebaseUser? = null
//
//        //Call OnCompleteListener if the user is signed in successfully
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password).addOnCompleteListener { task ->
//            //If the user has been authenticated...//
//            return when {
//                task.isSuccessful-> DataState.Success(task.result?.user)
//                else -> DataState.Failed(task.exception as Throwable, null)
//            }
//
////            return if (task.isSuccessful) {
////                //...then call requestLocationUpdates//
////                fUser = task.result?.user
////            } else {
////                //If sign in fails, then log the error//
////                task.exception?.message?.let { msg ->
////                    Timber.d("Firebase authentication failed $msg")
////                    throw RuntimeException(msg)
////                }
////            }
//        }
//    }

    fun handleUserTaskResponse(task: Task<AuthResult>): FirebaseUser? {
        return task.result?.user
    }
}