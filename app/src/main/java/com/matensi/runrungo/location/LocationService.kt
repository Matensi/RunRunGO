package com.matensi.runrungo.location


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.matensi.runrungo.MainActivity
import com.matensi.runrungo.R


class LocationService : Service() {
    private var distance = 0.0f
    private var  lastLocation: Location? = null
    private lateinit var locProvider: FusedLocationProviderClient
    private lateinit var locRequest: LocationRequest
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startNotification()
        startLocationUpdates()
        isRunning = true
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        initLocation()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        locProvider.removeLocationUpdates(locCallback)
    }

    private val locCallback = object : LocationCallback(){
        override fun onLocationResult(lResault: LocationResult) {
            super.onLocationResult(lResault)
            val currentLocation = lResault.lastLocation
            if(lastLocation != null ){
                distance += (currentLocation ?: lastLocation)?.let { lastLocation?.distanceTo(it) }!!
            }
            lastLocation = currentLocation
            Log.d("MyLog", "Distance: $distance")

        }
    }

    private fun startNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nChannel = NotificationChannel(
                CHANNEL_ID,
                "Location Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val nManager = getSystemService(NotificationManager::class.java) as NotificationManager
            nManager.createNotificationChannel(nChannel)
        }

        val nIntent = Intent(this, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this, 0, nIntent, FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(
            this,
            CHANNEL_ID
        ).setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Сервис запущен")
            .setContentIntent(pIntent).build()
        startForeground(99, notification)

    }

    private fun initLocation(){
        locRequest = LocationRequest.create()
        locRequest.interval = 5000
        locRequest.fastestInterval = 5000
        locRequest.priority = PRIORITY_HIGH_ACCURACY
        locProvider = LocationServices.getFusedLocationProviderClient(baseContext)
    }

    private fun startLocationUpdates(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED

        ) return

        locProvider.requestLocationUpdates(
            locRequest, locCallback, Looper.myLooper()
        )
    }
    companion object {
        const val CHANNEL_ID = "channel_1"
        var isRunning = false
        var startTime = 0L
    }
}