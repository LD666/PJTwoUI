package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject

interface RemoveModelInterface {

    fun onSuccess(removeProResponse: LiveData<JsonObject>)

}