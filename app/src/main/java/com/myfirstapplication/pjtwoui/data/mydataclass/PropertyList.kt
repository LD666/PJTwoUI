package com.myfirstapplication.pjtwoui.data.mydataclass

import com.google.gson.annotations.SerializedName

data class PropertyList (
    @SerializedName("Property") var property: List<Property>
)