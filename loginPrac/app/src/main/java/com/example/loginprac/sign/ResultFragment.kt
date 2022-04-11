package com.example.loginprac.sign

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.loginprac.R
import com.example.loginprac.databinding.FragmentResultBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResultFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentResultBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ResultLog", "onCreate")
//        binding.mapView.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ResultLog", "onStart")
        binding.mapView.onStart()
    }

//    lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater)

        Log.i("ResultLog", "onCreateView")
        auth = Firebase.auth
        val context = requireActivity().applicationContext

        binding.currentIdText.text = auth.currentUser?.email.toString()
        binding.logoutBtn.setOnClickListener { onClickLogout(context) }
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        return binding.root
    }

    private fun onClickLogout(context: Context) {
        logout(context)
    }

    override fun onMapReady(gMap: GoogleMap) {
        Log.i("ResultLog", "onMapReady")

        val sydney = LatLng(-34.0, 151.0)
        val other = LatLng(-34.0, 151.4)
//        gMap.addMarker(
//            MarkerOptions()
//            .position(sydney)
//            .title("Marker in Sydney"))

    }

    private fun logout(context: Context) {
        auth?.signOut()
        Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_resultFragment_to_signFragment)
    }

    override fun onResume() {
        super.onResume()
        Log.i("ResultLog", "onResume")
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.i("ResultLog", "onPause")
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ResultLog", "onDestroy")
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("ResultLog", "onLowMemory")
        binding.mapView.onLowMemory()
    }

}