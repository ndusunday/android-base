package com.zistus.atracker.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zistus.atracker.R
import com.zistus.atracker.entity.ResponseLiveData
import com.zistus.atracker.entity.ResponseType
import com.zistus.atracker.extension.observe
import com.zistus.atracker.ui.BaseFragment
import com.zistus.domain.entity.Entity
import com.zistus.domain.state.DataState
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    override fun layoutId(): Int  = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.testObjectDataState()
        observe(homeViewModel.responseLiveData, ::updateResponse)
        firstButton?.setOnClickListener {
            homeViewModel.fetchUser(30)
//            activity?.stopService()
        }

        secondButton?.setOnClickListener {
            findNavController().navigate(R.id.locationFragment)
        }
    }

    private fun updateResponse(responseLiveData: ResponseLiveData?) {
        responseLiveData?.let { response ->
            when (response.type) {
                ResponseType.TEST_ENUM-> {
                    response.data?.let { data ->
                        updateTestViews(data as Entity.TestObject)
                    }
                }
            }
        }
    }

    private fun updateTestViews(data: Entity.TestObject) {
        updateViewForTestObject((data))
    }

    private fun updateViewForTestObject(obj: Entity.TestObject) {
        testLabel.text = obj.id
    }
}