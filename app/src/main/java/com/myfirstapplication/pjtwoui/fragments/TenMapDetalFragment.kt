package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.TennantActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_ten_map_detal.view.*

class TenMapDetalFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_ten_map_detal, container, false)

        var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)

        var theAdd = savePro.getString("theAd", null)
        var theCi = savePro.getString("theCi", null)
        var theSt = savePro.getString("theSt", null)
        var theCn = savePro.getString("theCn", null)
        var theSta = savePro.getString("theSta", null)
        var thePrz = savePro.getString("thePrz", null)

        view.edit_text_dt_address_ten.text = "Address: " + theAdd
        view.edit_text_dt_city_ten.text = "City: " + theCi
        view.edit_text_dt_state_ten.text = "State: " + theSt
        view.edit_text_dt_country_ten.text = "Country: " + theCn
        view.edit_text_dt_status_ten.text = "Status" + theSta
        view.edit_text_dt_price_ten.text = "Price: " + thePrz


        view.dt_property_back_ten_ten.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, TennantActivity::class.java)
            startActivity(myIntent)

        })

        return view

    }

}