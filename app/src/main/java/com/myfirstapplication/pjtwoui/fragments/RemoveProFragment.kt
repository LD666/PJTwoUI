package com.myfirstapplication.pjtwoui.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.databinding.FragmentRemoveProBinding
import com.myfirstapplication.pjtwoui.myinterface.RemoveModelInterface
import com.myfirstapplication.pjtwoui.viewmodel.RemoveViewModel
import java.lang.Exception

class RemoveProFragment: Fragment(), RemoveModelInterface {

    override fun onSuccess(removeProResponse: LiveData<JsonObject>) {

        removeProResponse.observe(this, Observer {

            var msg = it.get("msg").toString()
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

            if(msg == "deleted property succesfully"){
                var landlordRemoveFragment = LandlordRemoveFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.add_remove_container, landlordRemoveFragment)?.commit()
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var view = inflater.inflate(R.layout.fragment_remove_pro, container, false)
        var binding: FragmentRemoveProBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_remove_pro, container, false)
        var rmViewModel = ViewModelProviders.of(this).get(RemoveViewModel::class.java)
        binding.removePropertyViewModel = rmViewModel

        rmViewModel.removeModelInterface = this


        var id: String?
        var propertyaddress: String?
        var propertycity: String?
        var propertystate: String?
        var propertycountry: String?
        var propertystatus: String?
        var propertypurchaseprice: String?
        var propertymortageinfo: String?

        try{
            id = arguments!!.getString("id").toString()
            Log.i("show", id)
            var saveProId = MyApplication.context.getSharedPreferences("saveProId", Context.MODE_PRIVATE)
            var editor = saveProId.edit()
            editor.putString("saveID", id)
            editor.commit()

            Log.i("MyID",saveProId.getString("saveID", null))
            propertyaddress = arguments!!.getString("propertyaddress").toString()
            propertycity = arguments!!.getString("propertycity").toString()
            propertystate = arguments!!.getString("propertystate").toString()
            propertycountry = arguments!!.getString("propertycountry").toString()
            propertystatus = arguments!!.getString("propertystatus").toString()
            propertypurchaseprice = arguments!!.getString("propertypurchaseprice").toString()
            propertymortageinfo = arguments!!.getString("propertymortageinfo").toString()

            if(id != null){
                binding.editTextRmId.setText("Property ID: " + id)
                binding.editTextRmAddress.setText("Property Address: " + propertyaddress)
                binding.editTextRmCity.setText("Property City: " + propertycity)
                binding.editTextRmState.setText("Property State: " + propertystate)
                binding.editTextRmCountry.setText("Property Country: " + propertycountry)
                binding.editTextRmStatus.setText("Property Status: " + propertystatus)
                binding.editTextRmPrice.setText("Property Price: " + propertypurchaseprice)
                binding.editTextRmMortageInfo.setText("Property Mortage Info: " + propertymortageinfo)
            }

        }catch (e: Exception){
            Log.e(TAG, e.toString())
        }


        binding.rmPropertyBack.setOnClickListener(View.OnClickListener {

            var landlordRemoveFragment = LandlordRemoveFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.add_remove_container, landlordRemoveFragment)?.commit()

        })


        return binding.root
//        return view
    }

}