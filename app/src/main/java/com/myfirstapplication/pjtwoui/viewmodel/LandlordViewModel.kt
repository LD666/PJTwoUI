package com.myfirstapplication.pjtwoui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories

class LandlordViewModel: ViewModel() {


    fun list() : LiveData<PropertyList>{
        return UserRepositories().list()
    }

}