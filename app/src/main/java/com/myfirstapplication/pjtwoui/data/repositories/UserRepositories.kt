package com.myfirstapplication.pjtwoui.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
import com.myfirstapplication.pjtwoui.data.mydataclass.mysharedpreferences.MyShared
import com.myfirstapplication.pjtwoui.data.network.UserApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepositories {

    fun userRegister(email: String, landlordEmail: String, password: String, type: String): LiveData<String>{

        val registerResponse = MutableLiveData<String>()

        UserApi().userRegister(email, landlordEmail, password, type)
            .enqueue(object: Callback<ResponseBody>{

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                   if(response.isSuccessful){
                       registerResponse.value = response.body()!!.string().trim()
                       Log.i("ur", "success body")
                   }else{
                       registerResponse.value = response.errorBody().toString()
                       Log.i("ur", "error body")
                   }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    registerResponse.value = t.message

                    Log.i("ur",t.message)
                }

            })

        return registerResponse

    }


    fun userLogin(email: String, password: String): LiveData<JsonObject>{

        val loginResponse = MutableLiveData<JsonObject>()

        var str: ArrayList<String> = ArrayList()

        UserApi().userLogin(email, password)
            .enqueue(object: Callback<JsonObject>{

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }


                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    loginResponse.value = response.body()
                    var userID = loginResponse.value?.get("userid")?.asString
                    var type = loginResponse.value?.get("usertype")?.asString

                    var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
                    var editor = userInfo.edit()

                    editor.putString("uid", userID)
                    editor.putString("utype", type)
                    editor.commit()

//                    Log.i("uinfo", userID.toString())
//                    Log.i("uinfo", type.toString())

                }

            })

        return loginResponse

    }


    fun forgotPassword(email: String): LiveData<JsonObject>{

        val forgotPasswordResponse = MutableLiveData<JsonObject>()

        UserApi().forgotPassword(email).enqueue(object: Callback<JsonObject>{

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                forgotPasswordResponse.value = response.body()


//                var getPass = forgotPasswordResponse.value?.get("userpassword").toString()
//                Log.i("getBackPassword", getPass)
            }

        })

        return forgotPasswordResponse

    }


    fun list (): LiveData<PropertyList>{

        var listResponse = MutableLiveData<PropertyList>()

        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)

        var userID = userInfo.getString("uid", null)
        var type = userInfo.getString("utype", null)

        Log.i("uinfoList", userID.toString())
        Log.i("uinfoList", type.toString())

        UserApi().proList(userID!!, type!!).enqueue(object: Callback<PropertyList>{

            override fun onFailure(call: Call<PropertyList>, t: Throwable) {

            }


            override fun onResponse(call: Call<PropertyList>, response: Response<PropertyList>) {
                listResponse.value = response.body()

//                Log.i("www", listResponse.value.toString())

            }

        })

        return listResponse
    }

    fun addPro(
        address: String,
        city: String,
        state: String,
        country: String,
        pro_status: String,
        status: String,
        mortage_info: String,
        latitude: String,
        longitude: String
    ): LiveData<JsonObject>{

        var addProResponse = MutableLiveData<JsonObject>()

        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var userID = userInfo.getString("uid", null)
        var type = userInfo.getString("utype", null)


        UserApi().addProperty(address, city, state, country, pro_status, status, mortage_info, userID!!, type!!, latitude, longitude)
            .enqueue(object: Callback<JsonObject>{

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }


                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    addProResponse.value = response.body()
                }

            })

        return addProResponse

    }


    fun removePro(proId: String): LiveData<JsonObject>{

        var removeProResponse = MutableLiveData<JsonObject>()


        UserApi().removeProperty(proId).enqueue(object: Callback<JsonObject>{

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }


            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

            }

        })

        return removeProResponse
    }


}