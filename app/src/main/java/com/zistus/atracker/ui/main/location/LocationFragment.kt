package com.zistus.atracker.ui.main.location

import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zistus.atracker.R
import com.zistus.atracker.ui.BaseFragment
import com.zistus.common.config.ConfigUtils
import org.koin.android.ext.android.inject
import timber.log.Timber


class LocationFragment : BaseFragment() {
    private val locationManager: LocationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            activity?.finish()
        }
    }

    override fun layoutId(): Int = R.id.locationFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndGrantLocationPermission(view)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // If request is cancelled, the result arrays are empty.
        if ((grantResults.isNotEmpty() && requestCode == ConfigUtils.LOCATION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            Timber.i("Location Permission is set")
        } else {
            activity?.finish()
        }
    }
}
