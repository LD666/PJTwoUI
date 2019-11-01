package com.myfirstapplication.pjtwoui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.mydataclass.TenantsList
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories

class TenantListViewModel: ViewModel() {

    fun onCall(): LiveData<TenantsList>{
        return UserRepositories().tenantList()
    }

}