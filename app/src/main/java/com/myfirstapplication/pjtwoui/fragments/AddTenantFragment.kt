package com.myfirstapplication.pjtwoui.fragments

import android.annotation.SuppressLint
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
import com.myfirstapplication.pjtwoui.databinding.FragmentAddTenantBinding
import com.myfirstapplication.pjtwoui.myinterface.AddTenInterface
import com.myfirstapplication.pjtwoui.viewmodel.AddTenViewModel
import okhttp3.ResponseBody


class AddTenantFragment: Fragment(), AddTenInterface {
    override fun onSuccess(addTenResponse: LiveData<ResponseBody>) {
        addTenResponse.observe(this, Observer {

            var msg = it.toString()

            Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
        })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var view = inflater.inflate(R.layout.fragment_add_tenant, container, false)

        var binding: FragmentAddTenantBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_tenant, container, false)
        var viewModel = ViewModelProviders.of(this).get(AddTenViewModel::class.java)
        binding.addTenViewModel = viewModel

        viewModel.addTenInterface = this

        binding.addTenBack.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, LandlordActivity::class.java)
            startActivity(myIntent)

        })

        return binding.root
//        return view
    }

}