package com.myfirstapplication.pjtwoui.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.mydataclass.GetAllProForTenants
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
import com.myfirstapplication.pjtwoui.data.mydataclass.TenantsList
import com.myfirstapplication.pjtwoui.data.mydataclass.mysharedpreferences.GetAllProForTenantsList
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


    fun userLogin(email: String, password: String): LiveData<String>{

//        val loginResponse = MutableLiveData<JsonObject>()
        var loginResponseStr = MutableLiveData<String>()

        var str: ArrayList<String> = ArrayList()

        UserApi().userLogin(email, password)
            .enqueue(object: Callback<JsonObject>{

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }


                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    //loginResponseStr.value = response.body()
                    var userID = response.body()?.get("userid")?.asString
                    var type = response.body()?.get("usertype")?.asString
                    loginResponseStr.value = response.body()?.get("usertype")?.asString
                    var email = response.body()?.get("useremail")?.asString


                    var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
                    var editor = userInfo.edit()

                    editor.putString("uid", userID)
                    editor.putString("utype", type)
                    editor.putString("uemail", email)
                    editor.commit()

                    Log.i("uinfo", userID.toString())
                    Log.i("uinfo", type.toString())
                    Log.i("uinfo", email.toString())

                    loginResponseStr.value = type

                }

            })

        return loginResponseStr

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

        var fak = MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
        var tryFak = fak.getString("fake", null)

        if(tryFak == "fakeIt"){
            userID = "158"
            type = "landlord"

            Log.i("showFakeIn", fak.getString("fake", null))
            Log.i("showFakeIn", userID)
            Log.i("showFakeIn", type)

        }

        UserApi().proList(userID!!, type!!).enqueue(object: Callback<PropertyList>{

            override fun onFailure(call: Call<PropertyList>, t: Throwable) {

            }


            override fun onResponse(call: Call<PropertyList>, response: Response<PropertyList>) {
                listResponse.value = response.body()

//                Log.i("www123", listResponse.value.toString())

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

//        var fak = MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
//        if(fak.getString("fake", null) == "fakeIt"){
//            var faUserID = "1"
//            var faType = "landlord"
//            var faEmail = "1"
//
//            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
//            var editor = userInfo.edit()
//
//            editor.putString("uid", faUserID)
//            editor.putString("utype", faType)
//            editor.putString("uemail", faEmail)
//            editor.commit()
//
//
//        }


        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var userID = userInfo.getString("uid", null)
        var type = userInfo.getString("utype", null)


        var fak = MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
        var tryFak = fak.getString("fake", null)

        if(tryFak == "fakeIt"){
            userID = "158"
            type = "landlord"

            Log.i("showFakeIn", fak.getString("fake", null))
            Log.i("showFakeIn", userID)
            Log.i("showFakeIn", type)

        }


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
                removeProResponse.value = response.body()
            }

        })

        return removeProResponse
    }

    fun tenantList(): LiveData<TenantsList>{

        var tenantListResponse = MutableLiveData<TenantsList>()

        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var userID = userInfo.getString("uid", null)

        var fak = MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
        var tryFak = fak.getString("fake", null)

        if(tryFak == "fakeIt"){
            userID = "158"

            Log.i("showFakeIn", fak.getString("fake", null))
            Log.i("showFakeIn", userID)

        }


        UserApi().tenantList(userID!!).enqueue(object: Callback<TenantsList>{

            override fun onFailure(call: Call<TenantsList>, t: Throwable) {

            }


            override fun onResponse(call: Call<TenantsList>, response: Response<TenantsList>) {
                tenantListResponse.value = response.body()
                Log.i("val",  tenantListResponse.value.toString())
            }

        })

        return tenantListResponse
    }


    fun addTen(tName: String, tEmail: String, tAddress: String, tMobile: String): LiveData<ResponseBody>{

        var addTenResponse = MutableLiveData<ResponseBody>()

        var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
        var userID = userInfo.getString("uid", null)

        var fak = MyApplication.context.getSharedPreferences("fakLog", Context.MODE_PRIVATE)
        var tryFak = fak.getString("fake", null)

        if(tryFak == "fakeIt"){
            userID = "158"

            Log.i("showFakeIn", fak.getString("fake", null))
            Log.i("showFakeIn", userID)

        }

        var saveProId = MyApplication.context.getSharedPreferences("theProID", Context.MODE_PRIVATE)
        var proId = saveProId.getString("saveID", null)

        UserApi().addTenant(tName, tEmail, tAddress, tMobile, proId!!, userID!!).enqueue(object: Callback<ResponseBody>{


            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }


            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                Log.i("showIT", "name " + tName)
                Log.i("showIT","email " +  tEmail)
                Log.i("showIT", "address " + tAddress)
                Log.i("showIT", "mobile " +tMobile)
                Log.i("showIT", "ProID " + proId)
                Log.i("showIT", "userID " +userID)



                Log.i("showCode", response.code().toString())

                if(response.isSuccessful){
                    addTenResponse.value = response.body()
                    response.code()
                    Log.i("showCode", "isSuccess")
                }
            }
        })

        return addTenResponse

    }


    fun getAllPro():LiveData<GetAllProForTenantsList>{

        var getAllProResponse = MutableLiveData<GetAllProForTenantsList>()

        UserApi().getProForTen().enqueue(object: Callback<GetAllProForTenantsList>{

            override fun onResponse(
                call: Call<GetAllProForTenantsList>,
                response: Response<GetAllProForTenantsList>
            ) {
                getAllProResponse.value = response.body()
            }


            override fun onFailure(call: Call<GetAllProForTenantsList>, t: Throwable) {

            }
        })

        return getAllProResponse
    }


}