package com.example.alphauser.UI.Map

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.alphauser.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapClass :AppCompatActivity() {

    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_class)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback() {
            googleMap = it
            googleMap.setOnMapClickListener {
                var markeroptions= MarkerOptions()
                markeroptions.position(it)
                markeroptions.title("${it.latitude} : ${it.longitude}")
                googleMap.clear()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    it,10f
                ))
                googleMap.addMarker(markeroptions)
            }
        })
    }
}