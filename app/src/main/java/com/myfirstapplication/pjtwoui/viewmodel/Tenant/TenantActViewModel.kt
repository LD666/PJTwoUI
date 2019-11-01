package com.myfirstapplication.pjtwoui.viewmodel.Tenant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myfirstapplication.pjtwoui.data.mydataclass.mysharedpreferences.GetAllProForTenantsList
import com.myfirstapplication.pjtwoui.data.repositories.UserRepositories

class TenantActViewModel: ViewModel() {

    fun getTenList(): LiveData<GetAllProForTenantsList> {
        return UserRepositories().getAllPro()
    }

}