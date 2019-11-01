package com.myfirstapplication.pjtwoui.data.mydataclass

import com.google.gson.annotations.SerializedName

data class Tenant(@SerializedName("id") var tId: String,
                  @SerializedName("tenantname") var tenantame: String,
                  @SerializedName("tenantemail") var tenantemail : String,
                  @SerializedName("tenantaddress") var tenantaddress: String,
                  @SerializedName("tenantmobile") var tenantmobile: String,
                  @SerializedName("propertyid") var propertyid : String,
                  @SerializedName("landlordid") var landlordid: String )