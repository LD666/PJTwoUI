package com.myfirstapplication.pjtwoui.data.network


import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.ResponseBody


interface UserApi {

    @GET("pro_mgt_reg.php")
    fun userRegister(
        @Query("email") regEmail: String,
        @Query("landlord_email") regLandlordEmail: String,
        @Query("password") regPassword: String,
        @Query("account_for") regType: String
    ) : Call<ResponseBody>



    @GET("pro_mgt_login.php")
    fun userLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Call <JsonObject>



    @GET("pro_mgt_forgot_pass.php")
    fun forgotPassword(
        @Query("email") email: String
    ) : Call<JsonObject>



    @GET("property.php")
    fun proList(
        @Query("userid") uID: String,
        @Query("usertype") uType: String
        ) : Call<JsonArray>











    companion object{

        val url = "http://rjtmobile.com/aamir/property-mgmt/"


        operator fun invoke(): UserApi{

//            val gson = GsonBuilder()
//                .setLenient()
//                .create()

            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi::class.java)
        }

    }

}