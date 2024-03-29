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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LoginActivity
import com.myfirstapplication.pjtwoui.activity.TennantActivity
import com.myfirstapplication.pjtwoui.activity.WelcomeMainActivity
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import kotlinx.android.synthetic.main.fragment_tenant_info.view.*

class TenantInfoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        var view = inflater.inflate(R.layout.fragment_tenant_info, container, false)

        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var uEmail = userInfo.getString("uemail", null)
        var uType = userInfo.getString("utype", null)

        Log.i("showIN",uEmail.toString())
        Log.i("showIN",uType.toString())


        if(uEmail == null || uType == null){
            view.ten_s_out_google.visibility = View.VISIBLE
            view.ten_f.text = "You Sign In with google"
            view.ten_sec.text = "  "
        }else{
            view.ten_f.text = "User Type: " + uType
            view.ten_sec.text = "User Email: " + uEmail
            view.ten_s_out.visibility = View.VISIBLE
        }


        view.ten_s_out.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)

            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var editor = userInfo.edit()
            var aa = userInfo.getString("utype", "lalala")
            Log.i("showthetext", aa)
            editor.putString("utype", null)
            editor.commit()

            Log.i("showthetext", userInfo.getString("utype", "lalala"))

        })

        view.ten_s_out_google.setOnClickListener(View.OnClickListener {

            mGoogleSignInClient.signOut()

            var myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)

        })


        return view
    }


}