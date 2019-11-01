package com.myfirstapplication.pjtwoui.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.RemoveModelInterface

class RemoveViewModel: ViewModel() {

    var removeProResponse: LiveData<JsonObject>? = null
    var removeModelInterface: RemoveModelInterface? = null


    fun onRemovePressed(view: View){

        var saveProId = MyApplication.context.getSharedPreferences("saveProId", Context.MODE_PRIVATE)
        var passID = saveProId.getString("saveID", null)

        Log.i("showMyID",saveProId.getString("saveID", null))
        Log.i("showMyID", "prssed")

        removeProResponse = UserRepositories().removePro(passID!!)
        removeModelInterface?.onSuccess(removeProResponse!!)
    }

}