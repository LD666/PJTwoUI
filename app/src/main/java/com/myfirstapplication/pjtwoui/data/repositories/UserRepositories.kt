package com.myfirstapplication.pjtwoui.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
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
//                        Log.i("res", loginResponse.value.toString())



//                    var msg = loginResponse.value?.get("msg")?.asString

//                    if (msg  == "success"){
//                        var userid = loginResponse.value?.get("userid")?.asString
//                        var usertype = loginResponse.value?.get("usertype")?.asString
//                        var useremail = loginResponse.value?.get("useremail")?.asString
//                        var appapikey = loginResponse.value?.get("appapikey")?.asString
//
//
//                        str.add(msg)
//                        str.add(userid!!)
//                        str.add(usertype!!)
//                        str.add(useremail!!)
//                        str.add(appapikey!!)
//                    }



//                        Log.i("res", msg)
//                        Log.i("res", userid)
//                        Log.i("res", usertype)
//                        Log.i("res", useremail)
//                        Log.i("res", appapikey)


//                        var context: Context? = null
//                        var saveMyUser = context?.getSharedPreferences("saveUser", Context.MODE_PRIVATE)
//                        var editor = saveMyUser?.edit()
//
//                        editor?.putString("loginMsg", msg)
//                        editor?.putString("loginUserid", userid)
//                        editor?.putString("loginUsertype", usertype)
//                        editor?.putString("loginUseremail", useremail)
//                        editor?.putString("loginAppapikey", appapikey)
//                        editor?.commit()
//
//
//                        Log.i("isSave", saveMyUser?.getString("loginMsg", null))
//                        Log.i("isSave", saveMyUser?.getString("loginUserid", null))
//                        Log.i("isSave", saveMyUser?.getString("loginUsertype", null))
//                        Log.i("isSave", saveMyUser?.getString("loginUseremail", null))
//                        Log.i("isSave", saveMyUser?.getString("loginAppapikey", null))

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


    fun list (userID: String, type: String): LiveData<JsonArray>{

        var listResponse = MutableLiveData<JsonArray>()

        UserApi().proList(userID, type).enqueue(object: Callback<JsonArray>{

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        return listResponse
    }

}