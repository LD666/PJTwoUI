package com.myfirstapplication.pjtwoui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LoginActivity
import com.myfirstapplication.pjtwoui.activity.WelcomeMainActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_landlord_info.*
import kotlinx.android.synthetic.main.fragment_landlord_info.view.*
import kotlinx.android.synthetic.main.fragment_tenant_info.view.*

class LandlordInfoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_landlord_info, container, false)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)


        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var uEmail = userInfo.getString("uemail", null)
        var uType = userInfo.getString("utype", null)

        Log.i("showINFO", uEmail.toString())
        Log.i("showINFO", uType.toString())


        if(uEmail == null || uType == null){
            view.text_view_landlord_type.text = "You Login as google"
            view.lan_s_out_google.visibility = View.VISIBLE
            view.button_landlord_sign_out.visibility = View.GONE
        }else{
            view.text_view_landlord_type.text = "User Type: " + uType
            view.text_view_landlord_email.text = "User Email: " + uEmail
            view.button_landlord_sign_out.visibility = View.VISIBLE
            view.lan_s_out_google.visibility = View.GONE
        }


        view.button_landlord_sign_out.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)

            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var editor1 = userInfo.edit()
            editor1.clear()
            editor1.commit()

            var fak =  MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
            var editor2 = fak.edit()
            editor2.clear()
            editor2.commit()

//            Log.i("showthetext", userInfo.getString("utype", "lalala"))
        })


        view.lan_s_out_google.setOnClickListener(View.OnClickListener {

            mGoogleSignInClient.signOut()

            var myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)

        })


        return view
    }

}