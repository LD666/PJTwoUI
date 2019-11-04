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
import com.google.android.gms.maps.model.MarkerOptions
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
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

                            var show1 = getRes.allProForTen[i].propertyaddress
                            var show2 = getRes.allProForTen[i].propertycity
                            var show3 = getRes.allProForTen[i].propertystate
                            var show4 = getRes.allProForTen[i].propertycountry
                            var show5 = getRes.allProForTen[i].propertypurchaseprice
                            var show6 = getRes.allProForTen[i].propertystatus

                            var info = ("address: "+ show1 + "\n" + "city: " + show2 + "\n" + "state: " + show3 + "\n" +
                                                "country: " + show4 +"\n" + "price: " + show5 + "\n" + "status: " + show6 + "\n")

                            Log.i("show", sydney.toString())
                            googleMap.addMarker(MarkerOptions().position(sydney).title(info))


                            var add = getRes.allProForTen[i].propertyaddress.toString()

                            Log.i("showIn", add)

                        }
                    }

                }

            })




            viewModel.getTenList().observe(this, Observer {getRes ->

                for(i in 0 until (getRes.allProForTen.size)){


                    googleMap.setOnInfoWindowClickListener {
                        Log.i("show", "clicked")
                        var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
                        var editor = savePro.edit()
                        editor.putString("theAd",  getRes.allProForTen[i].propertyaddress)
                        editor.putString("theCi", getRes.allProForTen[i].propertycity)
                        editor.putString("theSt", getRes.allProForTen[i].propertystate)
                        editor.putString("theCn", getRes.allProForTen[i].propertycountry)
                        editor.putString("theSta", getRes.allProForTen[i].propertystate)
                        editor.putString("thePrz", getRes.allProForTen[i].propertypurchaseprice)
                        editor.putString("theMor", getRes.allProForTen[i].propertymortageinfo)
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
}