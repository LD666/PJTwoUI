package com.myfirstapplication.pjtwoui.fragments

import android.content.Intent
import android.os.Bundle
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
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.databinding.FragmentLandlordAddBinding
import com.myfirstapplication.pjtwoui.myinterface.AddProInterface
import com.myfirstapplication.pjtwoui.viewmodel.AddPropertyViewModel
import kotlinx.android.synthetic.main.fragment_landlord_add.view.*

class LandlordAddFragment: Fragment(), AddProInterface {
    override fun onNull() {
        Toast.makeText(this.context, "Address Can't be Empty", Toast.LENGTH_SHORT).show()
    }

    override fun onNotAnAdd() {
        Toast.makeText(this.context, "Not a valid Address", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(addProResponse: LiveData<JsonObject>) {

        addProResponse.observe(this, Observer {

            var msg = it.get("msg").asString

            Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
        })

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //var addBinding: FragmentLandlordAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_landlord_add, container, false)
//        //var addBinding: FragmentLandlordAddBinding = DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_landlord_add)
//        var addVm = ViewModelProviders.of(this).get(AddPropertyViewModel::class.java)
//        addBinding.addPropertyViewModel = addVm
//
//        addVm.addProInterface = this
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        var view = inflater.inflate(R.layout.fragment_landlord_add, container, false)

        var addBinding: FragmentLandlordAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_landlord_add, container, false)
        //var addBinding: FragmentLandlordAddBinding = DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_landlord_add)
        var addVm = ViewModelProviders.of(this).get(AddPropertyViewModel::class.java)
        addBinding.addPropertyViewModel = addVm

        addVm.addProInterface = this


        addBinding.root.add_property_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this.context, LandlordActivity::class.java)
            startActivity(myIntent)

        })

        return addBinding.root
    }

}