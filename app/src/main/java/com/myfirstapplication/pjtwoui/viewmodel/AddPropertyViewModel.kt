package com.myfirstapplication.pjtwoui.viewmodel

import android.content.ContentValues.TAG
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.AddProInterface
import java.lang.Exception
import kotlin.concurrent.thread

class AddPropertyViewModel: ViewModel() {

    var address: String? = null
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var status: String? = null
    var price: String? = null
    var mortage: String? = null
    var Latitude: String? = null
    var longitude: String? = null

    var addProResponse: LiveData<JsonObject>? = null

    var addProInterface: AddProInterface? = null

    var geocoderMatch: List<Address>? = null


    fun onProAdd (view: View){
        Log.i("onAdd", "Pressed")

        try {

            if(address != null && city != null && state != null || country != null){

                var fulladdress = "$address, $city, $state, $country"
                geocoderMatch = Geocoder(MyApplication.context).getFromLocationName(fulladdress, 1)
                Latitude = geocoderMatch!![0].latitude.toString()
                longitude = geocoderMatch!![0].longitude.toString()

                Log.i("onAdd", Latitude)
                Log.i("onAdd", longitude)
            }

            Log.i("onAdd", geocoderMatch.toString())

        }catch (e: Exception){
            Log.i(TAG, e.toString())
        }

//        if(geocoderMatch != null){
//            Latitude = geocoderMatch!![0].latitude.toString()
//            longitude = geocoderMatch!![0].longitude.toString()
//
//            Log.i("onAdd", Latitude)
//            Log.i("onAdd", longitude)
//        }

//            if(Latitude != null && longitude != null){
//
//                addProResponse = UserRepositories().addPro(address!!, city!!, state!!, country!!, status!!, price!!, mortage!!, Latitude!!, longitude!!)
//                addProInterface?.onSuccess(addProResponse!!)
//            }else{
//                addProInterface?.onNotAnAdd()
//            }



    }


}