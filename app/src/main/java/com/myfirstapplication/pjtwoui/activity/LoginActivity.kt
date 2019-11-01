package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
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
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.databinding.ActivityLoginBinding
import com.myfirstapplication.pjtwoui.myinterface.LoginInterface
import com.myfirstapplication.pjtwoui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(), LoginInterface {
    override fun onSuccess(loginResponse: LiveData<String>) {
        //var show = loginResponse.value!!.get("msg").asString
        loginResponse.observe(this, Observer {

            var msg = it
//            myTask().execute()


            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var usertype = userInfo.getString("utype", null)
            Log.i("showMeWTF", userInfo.getString("utype", null).toString())
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            if(msg == "landlord"){
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                var myIntent = Intent(this, LandlordActivity::class.java)
                startActivity(myIntent)
            }else if(msg == "tenant"){
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                var myIntent = Intent(this, TennantActivity::class.java)
                startActivity(myIntent)
            }

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

        button_Reg.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)

        })

        button_login_goto_forgot.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(myIntent)

        })

//        login_back.setOnClickListener(View.OnClickListener {
//
//            var myIntent = Intent(this, WelcomeMainActivity::class.java)
//            startActivity(myIntent)
//
//        })

    }

    private     inner class myTask : AsyncTask<String, Double, String>(){

        var total = 5


        override fun doInBackground(vararg params: String): String {

            for(i in 0 ..total){
                Thread.sleep(1000)
                //var e = "hhh"
                publishProgress(i.toDouble())
            }

            var str = "Welcome..."
            return str

        }


    }

}
