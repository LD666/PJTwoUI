package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.WelcomeMainActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_landlord_info.view.*

class LandlordInfoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_landlord_info, container, false)


        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var uEmail = userInfo.getString("uemail", null)
        var uType = userInfo.getString("utype", null)


        view.text_view_landlord_type.text = "User Type: " + uType
        view.text_view_landlord_email.text = "User Email: " + uEmail


        view.button_landlord_sign_out.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, WelcomeMainActivity::class.java)
            startActivity(myIntent)

            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var editor = userInfo.edit()
            var aa = userInfo.getString("utype", "lalala")
            Log.i("showthetext", aa)
            editor.putString("utype", null)
            editor.commit()

            Log.i("showthetext", userInfo.getString("utype", "lalala"))
        })

        return view
    }

}