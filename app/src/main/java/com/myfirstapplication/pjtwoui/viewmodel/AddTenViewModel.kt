package com.myfirstapplication.pjtwoui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories
import com.myfirstapplication.pjtwoui.myinterface.AddTenInterface
import okhttp3.ResponseBody

class AddTenViewModel: ViewModel() {


    var name: String? = null
    var email: String? = null
    var address: String? = null
    var mobile: String? = null

    var addTenInterface: AddTenInterface? = null

    var addTenResponse: LiveData<ResponseBody>? = null

    fun onClick(view: View){
        Log.i("onAddViewC", "working")

        if(name != null && email!= null && address != null && mobile != null){
            addTenResponse = UserRepositories().addTen(name!!, email!!, address!!, mobile!!)
            addTenInterface?.onSuccess(addTenResponse!!)
        }
    }

}