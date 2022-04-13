package com.example.gpsprac

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.gpsprac.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fusedLocationClient: FusedLocationProviderClient
    var currLocation: Location? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.button.setOnClickListener {
            checkCurrentLocation()
            binding.latitudeText.text = "위도: ${currLocation?.latitude.toString()}"
            binding.longitudeText.text = "경도: ${currLocation?.longitude.toString()}"
        }
        setContentView(binding.root)
    }

    fun checkCurrentLocation() {
        var hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
            ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
            ACCESS_COARSE_LOCATION)

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ||
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                if (location != null) {
                    currLocation = location
                    Log.i("CurrLoc", "${currLocation?.latitude}")
                    Log.i("CurrLoc", "${currLocation?.longitude}")
                }
            }
        }
    }
}