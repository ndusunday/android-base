package com.zistus.atracker.ui.main.home

import android.os.Bundle
import com.zistus.atracker.R
import com.zistus.atracker.ui.BaseActivity
import com.zistus.atracker.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel



class MainActivity: BaseActivity() {
    val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavControllerId(): Int = R.id.mainHostFragment
}