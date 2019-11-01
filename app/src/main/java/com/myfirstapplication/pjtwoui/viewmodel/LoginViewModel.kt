package com.myfirstapplication.pjtwoui.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.activity.LoginActivity
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.LandlordListInterface
import com.myfirstapplication.pjtwoui.myinterface.LoginInterface

class LoginViewModel: ViewModel() {

    var logEmail: String? = null
    var logPassword: String? = null

    var loginInterface: LoginInterface? = null

    var loginResponse: LiveData<String>? = null

    fun onLogin(view: View){

        if(logEmail == null || logPassword == null){
            Log.i("pressed","Login Pressed")
            loginInterface?.onFalse()
        }else{
            loginResponse = UserRepositories().userLogin(logEmail!!, logPassword!!)
            loginInterface?.onSuccess(loginResponse!!)

//            loginResponse!!.value?.get("msg").toString()

//            Log.i("asa", loginResponse!!.value?.get("msg").toString())

        }

    }

}