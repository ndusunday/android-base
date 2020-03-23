package com.zistus.atracker.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zistus.atracker.R
import com.zistus.atracker.services.TrackingService
import com.zistus.atracker.ui.main.home.HomeViewModel
import com.zistus.common.config.ConfigUtils
import com.zistus.common.config.MessageUtils
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.longSnackbar
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    val homeViewModel: HomeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun checkAndGrantLocationPermission(view: View) {
        context?.let { cntx ->
            if (ContextCompat.checkSelfPermission(
                    cntx,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                showRationaleOrGrantPermission(view)
            }else {
                startLocationStreaming()
            }
        }
    }

    private fun showRationaleOrGrantPermission(view: View) {
        activity?.let {acvty->
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    acvty,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                acvty.runOnUiThread {
                    acvty.alert {
                        positiveButton("Proceed"){
                            requestPermissions(
                                arrayOf(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ), ConfigUtils.LOCATION_REQUEST_CODE
                            )
                            negativeButton("Cancel") {
                                acvty.finish()
                            }
                        }
                    }.show()
                }
                view.longSnackbar(MessageUtils.LOCATION_REQUIRED_MESSAGE)
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), ConfigUtils.LOCATION_REQUEST_CODE
                )
            }
        }
    }

    private fun startLocationStreaming() {
        val username = resources.getString(R.string.test_email)
        val password = resources.getString(R.string.test_password)
        val trackingIntent = Intent(context, TrackingService::class.java).also {
            it.putExtra("user", username)
            it.putExtra("password", password)
        }
        activity?.startService(trackingIntent)
    }


    abstract fun layoutId(): Int

    lateinit var viewModelFactory: ViewModelProvider.Factory    // Todo Inject viewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)
}