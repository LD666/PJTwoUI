package com.myfirstapplication.pjtwoui.viewmodel

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories

class RemoveProViewModel: ViewModel() {


    fun getList(): LiveData<PropertyList>{
        return UserRepositories().list()
    }

}