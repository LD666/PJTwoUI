package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject

interface ForgotPasswordInterface {

    fun onEmailsuccess(forgotPasswordResponse: LiveData<JsonObject>)

    fun onIsEmpty()

}