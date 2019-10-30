package com.myfirstapplication.pjtwoui.data.mydataclass.mysharedpreferences

import android.content.Context
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication

object MyShared{

    var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)


    fun saveUserInfo(){

        userInfo.getString("userid", null)
        userInfo.getString("usertype", null)

    }

}