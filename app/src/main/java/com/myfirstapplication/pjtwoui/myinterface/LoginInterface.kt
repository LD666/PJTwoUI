package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject

interface LoginInterface {

    fun onSuccess(loginResponse: LiveData<String>)

    fun onFalse()

}