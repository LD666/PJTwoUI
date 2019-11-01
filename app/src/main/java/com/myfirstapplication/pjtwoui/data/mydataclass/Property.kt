package com.myfirstapplication.pjtwoui.data.mydataclass

import com.google.gson.annotations.SerializedName

data class Property (@SerializedName("id") var id: String,
                     @SerializedName("propertyaddress") var propertyaddress: String,
                     @SerializedName("propertycity") var propertycity: String,
                     @SerializedName("propertystate") var propertystate: String,
                     @SerializedName("propertycountry") var propertycountry: String,
                     @SerializedName("propertystatus") var propertystatus: String,
                     @SerializedName("propertypurchaseprice") var propertypurchaseprice: String,
                     @SerializedName("propertymortageinfo") var propertymortageinfo: String,
                     @SerializedName("propertylatitude") var propertylatitude: String,
                     @SerializedName("propertylongitude") var propertylongitude: String)