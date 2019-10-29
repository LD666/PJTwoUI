package com.myfirstapplication.pjtwoui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.RegisterInterface

class RegisterViewModel: ViewModel() {

    var email: String? = null
    var landlordEmail: String? = null
    var password: String? = null

    var isFalse = 0

    var type: String? = null
    var registerResponse: LiveData<String>? = null

    var registerInterface: RegisterInterface? = null

    fun isTenant (view: View){
//        Log.i("isTL", "isTenant")
        type = "tenant"
    }

    fun isLandlord (view: View){
//        Log.i("isTL", "isLandlord")
        type = "landlord"
    }

    fun onRegister (view: View){

//        Log.i("button", "button")

//        registerInterface?.onStarted()

        if(type == null){
            isFalse = 1
            registerInterface?.onFalse()
        }else{
            isFalse = 0
        }

        Log.i("isTL", "pressed")

        if(email == null || landlordEmail == null || password == null){
            isFalse = 1
            registerInterface?.onFalse()
        }else{
            isFalse = 0
        }

        if(email != null && landlordEmail != null && password != null){
            var regEmail = email?.toCharArray()
            var countD = 0
            var countA = 0

            for(i in 0 until (regEmail!!.size)){
                if(regEmail[i].toString() == "@"){
                    Log.i("array", regEmail[i].toString())
                    isFalse = 0
                    break
                }else{
                    Log.i("array", regEmail[i].toString())
                    registerInterface?.onEmailWrong()
                    isFalse = 1
                }
            }

            for(i in 0 until (regEmail!!.size)){
                if(regEmail[i].toString() == "."){
                    Log.i("array", regEmail[i].toString())
                    isFalse = 0
                    break
                }else{
                    Log.i("array", regEmail[i].toString())
                    registerInterface?.onEmailWrong()
                    isFalse = 1
                }
            }

            for(i in 0 until (regEmail!!.size)){
                if(regEmail[i].toString() == "."){
                    countD++
                }else if(regEmail[i].toString() == "@"){
                    countA++
                    registerInterface?.onEmailWrong()
                }
            }

            if(countA > 1 || countD > 1){
                isFalse = 1
                registerInterface?.onEmailWrong()
            }else{
                isFalse = 0
            }

        }




        if(isFalse == 0 && email != null && landlordEmail != null && password != null && type != null){

            registerResponse = UserRepositories().userRegister(email!!, landlordEmail!!, password!!, type!!)
            registerInterface?.onSuccess(registerResponse!!)
        }else{
            registerInterface?.onFalse()
        }

//        Log.i("data", "Email " + email)
//        Log.i("data", "LEmail " + landlordEmail)
//        Log.i("data", "pass " + password)
//        Log.i("data", "type " + type)


//        val registerResponse = UserRepositories().userRegister(email!!, landlordEmail!!, password!!, type!!)
//        registerInterface?.onSuccess(registerResponse!!)

    }



}