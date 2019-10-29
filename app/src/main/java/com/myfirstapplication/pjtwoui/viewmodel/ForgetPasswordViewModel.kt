package com.myfirstapplication.pjtwoui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.ForgotPasswordInterface

class ForgetPasswordViewModel: ViewModel() {

    var fogEmail: String? = null

    var forgotPasswordInterface: ForgotPasswordInterface? = null

    var forgotPasswordResponse: LiveData<JsonObject>? = null

    fun onFogButton(view: View){
        if(fogEmail == null){
            forgotPasswordInterface?.onIsEmpty()
        }else{
            forgotPasswordResponse = UserRepositories().forgotPassword(fogEmail!!)
            forgotPasswordInterface?.onEmailsuccess(forgotPasswordResponse!!)
        }
    }

}