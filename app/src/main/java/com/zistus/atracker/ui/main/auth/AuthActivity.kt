package com.zistus.atracker.ui.main.auth

import android.os.Bundle
import com.zistus.atracker.R
import com.zistus.atracker.ui.BaseActivity

class AuthActivity : BaseActivity() {
    override fun getNavControllerId(): Int = R.id.authHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}
