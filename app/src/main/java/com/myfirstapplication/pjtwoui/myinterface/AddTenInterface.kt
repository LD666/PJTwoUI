package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData
import okhttp3.ResponseBody

interface AddTenInterface {

    fun onSuccess(addTenResponse: LiveData<ResponseBody>)

}