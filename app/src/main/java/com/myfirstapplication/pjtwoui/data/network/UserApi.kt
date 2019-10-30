package com.myfirstapplication.pjtwoui.data.network


import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.data.mydataclass.PropertyList
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
        ) : Call<PropertyList>



    @GET("pro_mgt_add_pro.php")
    fun addProperty(
        @Query("address") address: String,
        @Query("city") city: String,
        @Query("state") state: String,
        @Query("country") country: String,
        @Query("pro_status") pro_status: String,
        @Query("purchase_price") status: String,
        @Query("mortage_info") mortage_info: String,
        @Query("userid") uid: String,
        @Query("usertype") uType: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ) : Call<JsonObject>


    @GET("remove-property.php")
    fun removeProperty(
        @Query("propertyid") propertyId: String
    ) : Call<JsonObject>






    companion object{

        val url = "http://rjtmobile.com/aamir/property-mgmt/"


        operator fun invoke(): UserApi{

            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi::class.java)
        }

    }

}