package com.zistus.atracker.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.zistus.atracker.entity.ResponseLiveData
import com.zistus.atracker.entity.ResponseType
import com.zistus.atracker.extension.applyNewScheduler
import com.zistus.atracker.extension.testString
import com.zistus.atracker.ui.BaseViewModel
import com.zistus.atracker.ui.main.firebase.FirebaseImpl
import com.zistus.atracker.ui.main.firebase.FirebaseInteractor
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import com.zistus.domain.usecases.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel(private val useCase: BaseUseCase) : BaseViewModel() {
    public val signedInUser: MutableLiveData<Entity.TestObject> = MutableLiveData()
    public val signInError: MutableLiveData<Exception> = MutableLiveData()
    public val responseLiveData: MutableLiveData<ResponseLiveData> = MutableLiveData()
    private val firebase: FirebaseInteractor by lazy {
        FirebaseImpl()
    }

    private val user = MutableLiveData<User>()

    fun fetchUser(id: Int){

    }

    suspend fun signUserIn(user: String, password: String, callback: (user: FirebaseUser?, error: java.lang.Exception?)-> Unit) {
        firebase.signInHandler(user, password).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    it.result?.runCatching {
                        callback(this.user, null)
                    }
                }
                else -> {
                    signInError.postValue(it.exception)
                    callback(null, it.exception)
                }
            }
        }
    }

    suspend fun fetchTest() {
        withContext(Dispatchers.Main) {
//            useCase.fetchTestWithCoroutine()
        }

    }

    fun testReturnString(): String {
       val test = ""
        return useCase.testString()
    }

    fun getTester() {

    }

    fun testObjectDataState() {
    }
}