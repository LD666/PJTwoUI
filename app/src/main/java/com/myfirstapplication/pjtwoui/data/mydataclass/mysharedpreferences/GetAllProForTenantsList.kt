package com.myfirstapplication.pjtwoui.data.mydataclass.mysharedpreferences

import com.google.gson.annotations.SerializedName
import com.myfirstapplication.pjtwoui.data.mydataclass.GetAllProForTenants

data class GetAllProForTenantsList (
    @SerializedName("Property") var allProForTen: List<GetAllProForTenants>
)