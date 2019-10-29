package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.ActivityLoginBinding
import com.myfirstapplication.pjtwoui.myinterface.LoginInterface
import com.myfirstapplication.pjtwoui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginInterface {
    override fun onSuccess(loginResponse: LiveData<JsonObject>) {
        //var show = loginResponse.value!!.get("msg").asString
        loginResponse.observe(this, Observer {

            var msg = it.get("msg").asString
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            var userid = it.get("userid")?.asString
            var usertype = it.get("usertype")?.asString
            var useremail = it.get("useremail")?.asString
            var appapikey = it.get("appapikey")?.asString

            var saveUserInfo = getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var editor = saveUserInfo.edit()

            if (userid!= null && usertype != null && useremail != null && appapikey != null){
                Log.i("res", msg)
                Log.i("res", userid)
                Log.i("res", usertype)
                Log.i("res", useremail)
                Log.i("res", appapikey)

                editor.putString("loginMsg", msg)
                editor.putString("loginUserid", userid)
                editor.putString("loginUsertype", usertype)
                editor.putString("loginUseremail", useremail)
                editor.putString("loginAppapikey", appapikey)
                editor.commit()

                if(usertype == "landlord"){
                    var myIntent = Intent(this, LandlordActivity::class.java)
                    startActivity(myIntent)
                }else{
                    var myIntent = Intent(this, TennantActivity::class.java)
                    startActivity(myIntent)
                }

            }



            Log.i("isSave", saveUserInfo.getString("loginMsg", null))
            Log.i("isSave", saveUserInfo.getString("loginUserid", null))
            Log.i("isSave", saveUserInfo.getString("loginUsertype", null))
            Log.i("isSave", saveUserInfo.getString("loginUseremail", null))
            Log.i("isSave", saveUserInfo.getString("loginAppapikey", null))

        })
    }

    override fun onFalse() {
        Toast.makeText(this, "Login False", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        viewModel.loginInterface = this

        button_login_goto_forgot.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(myIntent)

        })

        login_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, WelcomeMainActivity::class.java)
            startActivity(myIntent)

        })

    }
}
