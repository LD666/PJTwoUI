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
import com.myfirstapplication.pjtwoui.data.mydataclass.GetAllProForTenants
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.infowindow.InfoWindow
import com.myfirstapplication.pjtwoui.viewmodel.Tenant.TenantActViewModel
import kotlinx.android.synthetic.main.fragment_ten_map.*

class TenMapFragment: Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    override fun onInfoWindowClick(p0: Marker) {
        var property = p0.tag as GetAllProForTenants
        var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
        var editor = savePro.edit()
        Log.i("showInfoWin", property.propertyaddress.toString())
        editor.putString("theAd",  property.propertyaddress)
        editor.putString("theCi", property.propertycity)
        editor.putString("theSt", property.propertystate)
        editor.putString("theCn", property.propertycountry)
        editor.putString("theSta", property.propertystate)
        editor.putString("thePrz", property.propertypurchaseprice)
        editor.commit()


        Log.i("showData", savePro.getString("theAd", null).toString())


        var tenMapDetalFragment = TenMapDetalFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.ten_main, tenMapDetalFragment)?.commit()
    }

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

            with(googleMap){
                setInfoWindowAdapter(InfoWindow(this@TenMapFragment))
                setOnInfoWindowClickListener(this@TenMapFragment)
            }

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

                            Log.i("showData",  getRes.allProForTen[i].propertyaddress)

                            var show1 = getRes.allProForTen[i].propertyaddress
                            var show2 = getRes.allProForTen[i].propertycity
                            var show3 = getRes.allProForTen[i].propertystate
                            var show4 = getRes.allProForTen[i].propertycountry
                            var show5 = getRes.allProForTen[i].propertypurchaseprice
                            var show6 = getRes.allProForTen[i].propertystatus

                            var info = (show1 + "\n" + show2 + "\n" +  show3 + "\n" +
                                                show4 +"\n")


                            Log.i("show", sydney.toString())
                            var marker = googleMap.addMarker(MarkerOptions().position(sydney).title(show1))

                            marker.tag = getRes.allProForTen[i]

                        }

                    }

                }

//                googleMap.setOnInfoWindowClickListener {
//
//                    Log.i("show", it.title)
//
//                    var add = it.title.split("\n")[0]
//                    var cit = it.title.split("\n")[1]
//                    var sta = it.title.split("\n")[2]
//                    var cou = it.title.split("\n")[3]
////                    var pri = it.title.split("\n")[4]
////                    var stu = it.title.split("\n")[5]
//
//
//                    Log.i("showDit", add)
//                    Log.i("showDit", cit)
//                    Log.i("showDit", sta)
//                    Log.i("showDit", cou)
////                    Log.i("showDit", pri)
////                    Log.i("showDit", stu)
//
//                    var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
//                    var editor = savePro.edit()
//                    editor.putString("theAd",  add)
//                    editor.putString("theCi", cit)
//                    editor.putString("theSt", sta)
//                    editor.putString("theCn", cou)
////                    editor.putString("theSta", pri)
////                    editor.putString("thePrz", stu)
//                    editor.commit()
//
//                    var tenMapDetalFragment = TenMapDetalFragment()
//                    fragmentManager?.beginTransaction()?.replace(R.id.ten_main, tenMapDetalFragment)?.commit()
//
//                }

            })


//            val sydney = LatLng(-34.0, 151.0)
//            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))


        }
    }
}