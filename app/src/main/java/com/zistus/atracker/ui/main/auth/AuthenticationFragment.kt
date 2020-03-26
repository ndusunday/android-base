package com.zistus.atracker.ui.main.auth

import android.os.Bundle
import android.view.View
import com.zistus.atracker.R
import com.zistus.atracker.extension.*
import com.zistus.atracker.ui.BaseFragment
import com.zistus.common.utils.StringUtils
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import kotlinx.android.synthetic.main.fragment_authentication.*
import kotlinx.android.synthetic.main.fragment_authentication.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class AuthenticationFragment: BaseFragment() {
    private val authViewModel: AuthenticationViewModel by viewModel()
    override fun layoutId(): Int = R.layout.fragment_authentication

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(authViewModel.requestCode, ::requestCode)
        observe(authViewModel.loginResponse, ::loginResponse)

        submitButton?.setOnClickListener { it->
            context?.longToast("Loading, Wait")
            when(it.submitButton.text.toString()) {
                "Submit"-> authViewModel.loginUserPhone(phoneEntry.text.toString().trimWhiteSpace())
                "SignIn"-> signInWithVerificationCode()
            }
        }
    }

    private fun loginResponse(dataState: DataState<Entity.User>?) {
        when (dataState) {
            is DataState.Success-> {context?.longToast("Successful login")}
            is DataState.Loading-> {context?.longToast("Loading Login")}
            is DataState.Failed-> {context?.longToast("Failed login")}
        }
    }

    private fun signInWithVerificationCode() {
        if (pinEntry.text.toString().isCode()) {
            Timber.e("Request code: ")
            authViewModel.loginWithCredential(pinEntry.text.toString())
        }else {
            context?.toast("Invalid Code was entered")
        }
    }

    private fun requestCode(toRequest: Boolean?) {
        if (toRequest!=null && toRequest) {
            pinEntryContainer.visible()
            submitButton.text = "SignIn"
        }else {
            Timber.e("Code is not requested yet.")
        }
    }
}