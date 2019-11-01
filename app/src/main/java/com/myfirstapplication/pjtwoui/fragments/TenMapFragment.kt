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
import com.myfirstapplication.pjtwoui.viewmodel.Tenant.TenantActViewModel
import kotlinx.android.synthetic.main.fragment_ten_map.*

class TenMapFragment: Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)

        ten_map_view.onCreate(savedInstanceState)
        ten_map_view.onResume()

        ten_map_view.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ten_map, container, false)

        return view
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {

//            googleMap = it
            googleMap = it

            val viewModel = ViewModelProviders.of(this).get(TenantActViewModel::class.java)

            viewModel.getTenList().observe(this, Observer {getRes ->

                for(i in 0 until (getRes.allProForTen.size)){
                    Log.i("show", getRes.allProForTen[i].toString())
                    Log.i("showL", getRes.allProForTen[i].propertylatitude)


                    if(getRes.allProForTen[i].propertylatitude.isNotEmpty() && getRes.allProForTen[i].propertylongitude.isNotEmpty()
                        && getRes.allProForTen[i].propertylatitude.isNotBlank() && getRes.allProForTen[i].propertylongitude.isNotBlank()){
                        if(getRes.allProForTen[i].propertylatitude != null && getRes.allProForTen[i].propertylongitude != null){
                            var lat = getRes.allProForTen[i].propertylatitude.toDouble()
                            var lon = getRes.allProForTen[i].propertylongitude.toDouble()
                            val sydney = LatLng(lat, lon)
                            Log.i("show", sydney.toString())
                            googleMap.addMarker(MarkerOptions().position(sydney).title(getRes.allProForTen[i].propertyaddress.toString()))
                        }
                    }
                }

            })

//            val sydney = LatLng(-34.0, 151.0)
//            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))


        }
    }
}