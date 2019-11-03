package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_show_pro_deta.view.*

class OnMapShowDitFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_show_pro_deta, container, false)

        var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)

        var theAdd = savePro.getString("theAd", null)
        var theCi = savePro.getString("theCi", null)
        var theSt = savePro.getString("theSt", null)
        var theCn = savePro.getString("theCn", null)
        var theSta = savePro.getString("theSta", null)
        var thePrz = savePro.getString("thePrz", null)
        var theMor = savePro.getString("theMor", null)

        view.edit_text_dt_address.text = "Address: " + theAdd
        view.edit_text_dt_city.text = "City: " + theCi
        view.edit_text_dt_state.text = "State: " + theSt
        view.edit_text_dt_country.text = "Country: " + theCn
        view.edit_text_dt_status.text = "Status" + theSta
        view.edit_text_dt_price.text = "Price: " + thePrz
        view.edit_text_dt_mortage_info.text = "Mortage Info:" + theMor


        view.dt_property_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, LandlordActivity::class.java)
            startActivity(myIntent)

        })

        return view
    }
}