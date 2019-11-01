package com.myfirstapplication.pjtwoui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.viewmodel.LandlordViewModel
import kotlinx.android.synthetic.main.fragment_map.*

class MyMapFragment: Fragment(), OnMapReadyCallback  {

    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)

        map_view.onCreate(savedInstanceState)
        map_view.onResume()

        map_view.getMapAsync(this)

/*        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/

    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let { it ->
            googleMap = it

            val viewModel = ViewModelProviders.of(this).get(LandlordViewModel::class.java)

           viewModel.list().observe(this, Observer { getRes ->

               for(i in 0 until (getRes.property.size)){
                   Log.i("show", getRes.property[i].toString())
                   Log.i("showL", getRes.property[i].propertylatitude)
                   var lat = getRes.property[i].propertylatitude.toDouble()
                   var lon = getRes.property[i].propertylongitude.toDouble()
                   val sydney = LatLng(lat, lon)
                   googleMap.addMarker(MarkerOptions().position(sydney).title(getRes.property[i].propertyaddress.toString()))
               }

           })



//            val sydney = LatLng(-34.0, 151.0)
//            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        }
    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_map, container, false)

        return view
    }

}