package com.example.gpsprac

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.gpsprac.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityMainBinding
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var mMap: GoogleMap
    var currLocation: Location? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        binding.button.setOnClickListener {
            checkCurrentLocation()
            binding.latitudeText.text = "위도: ${currLocation?.latitude.toString()}"
            binding.longitudeText.text = "경도: ${currLocation?.longitude.toString()}"
            addMarkerAtCurrentLocation()
        }
        setContentView(binding.root)
    }

    fun addMarkerAtCurrentLocation() {
        var currLatLng: LatLng? = null
        if (currLocation != null)
            currLatLng = LatLng(currLocation?.latitude.toString().toDouble(), currLocation?.longitude.toString().toDouble())

        if (currLatLng != null) {
            Log.i("CurrLoc", "${currLatLng.latitude}, ${currLatLng.longitude}")
            mMap.addMarker(MarkerOptions().position(currLatLng).title("current location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currLatLng))
        }
        Log.i("CurrLoc", "LatLng is null")
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
}