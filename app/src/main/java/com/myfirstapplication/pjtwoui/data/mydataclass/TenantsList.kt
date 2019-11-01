package com.myfirstapplication.pjtwoui.data.mydataclass

import com.google.gson.annotations.SerializedName

data class TenantsList(
    @SerializedName("Tenants") var tenants: List<Tenant>
)