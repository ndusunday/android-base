package com.zistus.atracker.ui.main.auth

import androidx.lifecycle.MutableLiveData
import com.zistus.atracker.extension.applyNewScheduler
import com.zistus.atracker.ui.BaseViewModel
import com.zistus.atracker.ui.main.home.User
import com.zistus.domain.usecases.user.UserUseCase

class AuthenticationViewModel(private val userUseCase: UserUseCase): BaseViewModel() {
    val light: MutableLiveData<String> = MutableLiveData()

    fun registerUser(user: User) {

    }

    fun testSetup() {
        userUseCase.testString()
            .applyNewScheduler()
            .subscribe { succ -> light.postValue(succ) }.track()
    }
}