package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject

interface AddProInterface {

    fun onSuccess(addProResponse: LiveData<JsonObject>)

    fun onNotAnAdd()

    fun onNull()

}