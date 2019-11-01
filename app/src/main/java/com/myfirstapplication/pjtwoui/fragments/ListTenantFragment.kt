package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_list_tenant.view.*



class ListTenantFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_list_tenant, container, false)

        var saveTenDit = MyApplication.context.getSharedPreferences("saveTenDit", Context.MODE_PRIVATE)

        var tName = saveTenDit.getString("tName", null)
        var tEmail = saveTenDit.getString("tEmail", null)
        var tAddress = saveTenDit.getString("tAddress", null)
        var tMobile = saveTenDit.getString("tMobile", null)
        var tImg = saveTenDit.getString("tImg", null)


        Log.i("showPassInF", tName)
        Log.i("showPassInF", tEmail)
        Log.i("showPassInF", tAddress)
        Log.i("showPassInF", tMobile)
        Log.i("showPassInF", tImg)

        view.textView_ten_name.text = tName
        view.textView_ten_email.text = "Email: " + tEmail
        view.textView_ten_address.text = "Adress: " + tAddress
        view.textView_ten_phone.text = "Phone: " + tMobile

        this.context?.let { Glide.with(it).load(tImg).into(view.imageView4) }


        view.ten_con_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, LandlordActivity::class.java)
            startActivity(myIntent)

        })

        view.button_ten_phone.setOnClickListener(View.OnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            startActivity(phoneIntent)
        })

        view.button_ten_message.setOnClickListener(View.OnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        })

        view.button_ten_email.setOnClickListener(View.OnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SENDTO
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        })



        return view
    }

}