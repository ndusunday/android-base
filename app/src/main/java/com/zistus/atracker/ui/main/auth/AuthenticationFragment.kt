package com.zistus.atracker.ui.main.auth

import android.os.Bundle
import android.view.View
import com.zistus.atracker.R
import com.zistus.atracker.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class AuthenticationFragment: BaseFragment() {
    val authViewModel: AuthenticationViewModel by viewModel()
    override fun layoutId(): Int = R.layout.fragment_authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        authViewModel.loginUser("tester@test.com", "testing12")
//        authViewModel.loginUserPhone(activity!!, "+2348031162141")
        authViewModel.loginUserPhone("+2348031162141", "123456")
    }
}