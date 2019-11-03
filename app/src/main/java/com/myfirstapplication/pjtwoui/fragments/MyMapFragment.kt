package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.viewmodel.LandlordViewModel
import kotlinx.android.synthetic.main.fragment_map.*

class MyMapFragment: Fragment(), OnMapReadyCallback {
//    override fun onMarkerClick(p0: Marker?): Boolean {
//
//        var property = p0?.tag as Property
//
//        var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
//        var editor = savePro.edit()
//        editor.putString("theAd",  property.propertyaddress)
//        editor.putString("theCi", property.propertycity)
//        editor.putString("theSt", property.propertystate)
//        editor.putString("theCn", property.propertycountry)
//        editor.putString("theSta", property.propertystate)
//        editor.putString("thePrz", property.propertypurchaseprice)
//        editor.putString("theMor", showDataroperty.propertymortageinfo)
//        editor.commit()
//
//        Log.i("showData", savePro.getString("theAd", null).toString())
//
//
//        var onMapShowDitFragment = OnMapShowDitFragment()
//        fragmentManager?.beginTransaction()?.replace(R.id.landlord_main, onMapShowDitFragment)?.commit()
//
//        return false
//    }

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

                   var marker = googleMap.addMarker(MarkerOptions().position(sydney).title(getRes.property[i].propertyaddress.toString()))

                   marker.tag = getRes.property[i]

                   googleMap.setOnInfoWindowClickListener {
                       Log.i("show", "clicked")
                       var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
                       var editor = savePro.edit()
                       editor.putString("theAd",  getRes.property[i].propertyaddress)
                       editor.putString("theCi", getRes.property[i].propertycity)
                       editor.putString("theSt", getRes.property[i].propertystate)
                       editor.putString("theCn", getRes.property[i].propertycountry)
                       editor.putString("theSta", getRes.property[i].propertystate)
                       editor.putString("thePrz", getRes.property[i].propertypurchaseprice)
                       editor.putString("theMor", getRes.property[i].propertymortageinfo)
                       editor.commit()

                       Log.i("showData", savePro.getString("theAd", null).toString())


                       var onMapShowDitFragment = OnMapShowDitFragment()
                       fragmentManager?.beginTransaction()?.replace(R.id.landlord_main, onMapShowDitFragment)?.commit()
                   }

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