package com.myfirstapplication.pjtwoui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories

class LandlordAddTenViewModel: ViewModel() {


    fun onCallList(): LiveData<PropertyList>{
        return UserRepositories().list()
    }

}