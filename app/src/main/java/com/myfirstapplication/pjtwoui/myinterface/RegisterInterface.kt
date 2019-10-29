package com.myfirstapplication.pjtwoui.myinterface

import androidx.lifecycle.LiveData

interface RegisterInterface {

    fun onStarted()

    fun onSuccess(registerResponse: LiveData<String>)

    fun onEmailWrong()

    fun onFalse()

}