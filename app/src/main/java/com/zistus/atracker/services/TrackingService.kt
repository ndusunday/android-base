package com.zistus.atracker.services

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.IBinder
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zistus.atracker.R
import com.zistus.atracker.ui.main.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.*


class TrackingService : Service() {
    private val viewModel: HomeViewModel by inject<HomeViewModel>()
    lateinit var username: String
    lateinit var password: String

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.runCatching {
            username = this.extras.getString("user")?:resources.getString(R.string.tester_email)
            password = this.extras.getString("password")?:resources.getString(R.string.tester_password)
            loginToFirebase(username, password)
        }
        return START_NOT_STICKY
    }
    override fun onCreate() {
        super.onCreate()
        username = resources.getString(R.string.test_email)
        password = resources.getString(R.string.test_password)

//        loginToFirebase(username, password)
//        buildNotification()
    }

    private fun buildNotification() {
        val stop = "stop"
        registerReceiver(stopReceiver, IntentFilter(stop))
        val broadcastIntent = PendingIntent.getBroadcast(
            this, 0, Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Create the persistent notification//
        val builder = Notification.Builder(this)
            .setContentTitle("aTracker")
            .setContentText("Tracking Started")

            // Make this notification ongoing so it can’t be dismissed by the user
            .setOngoing(true)
            .setContentIntent(broadcastIntent)
            .setSmallIcon(R.drawable.navigation_empty_icon)
        startForeground(1, builder.build())
    }

    private var stopReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            //Unregister the BroadcastReceiver when the notification is tapped//
            unregisterReceiver(this)

            //Stop the Service//
            stopSelf()
        }
    }

    private fun loginToFirebase(user: String, password: String) {
        CoroutineScope(Main).launch {
            viewModel.signUserIn(user, password){user, error->
                user?.runCatching {requestLocationUpdates(this)}
            }
        }
//        //Call OnCompleteListener if the user is signed in successfully//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password).addOnCompleteListener { task ->
//            //If the user has been authenticated...//
//            if (task.isSuccessful) {
//                //...then call requestLocationUpdates//
//                task.result?.user?.apply{
//                    requestLocationUpdates(this)
//                }
//            } else {
//                //If sign in fails, then log the error//
//                task.exception?.message?.let { msg ->
//                    Timber.d("Firebase authentication failed $msg")
//                }
//            }
//        }
    }

    private fun requestLocationUpdates(user: FirebaseUser) {
        val request = LocationRequest()

        //Specify how often your app should request the device’s location//
        request.interval = 10000

        //Get the most accurate location data available//
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val client = LocationServices.getFusedLocationProviderClient(this)
        val path = "location"
        val userPath = "user"
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        //If the app currently has access to the location permission...//
        if (permission == PackageManager.PERMISSION_GRANTED) {
            //...then request location updates//
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {

                    //Get a reference to the database, so your app can perform read and write operations//
                    val ref = FirebaseDatabase.getInstance().getReference(path)
                    val userRef = FirebaseDatabase.getInstance().getReference(userPath)
                    val addressRef = FirebaseDatabase.getInstance().getReference("address")
//                    ref.push()
//                    userRef.push()
//                    addressRef.push()

//                    userRef.child("name")
                    val location = locationResult?.apply {
                        ref.setValue("${this.lastLocation.latitude}: ${this.lastLocation.longitude}")
                        userRef.setValue("User is ${user.email}")

                        val geocoder = Geocoder(applicationContext, Locale.getDefault()).getFromLocation(this.lastLocation.latitude, this.lastLocation.longitude, 100)
                        val address = geocoder[0]
                        val addressFragments = with(address) {
                            (0..maxAddressLineIndex).map { getAddressLine(it) }
                        }
                        Toast.makeText(applicationContext, "Last Location is ${addressFragments[0]}", Toast.LENGTH_LONG).show()
                        addressRef.setValue("Address ${addressFragments[0]}")

                        ref.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                Timber.i("Failed to read value ${p0?.toException()}")
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                Timber.i("Value is: $p0")
                            }
                        })
                    }
                }
            }, null)
        }
    }
}