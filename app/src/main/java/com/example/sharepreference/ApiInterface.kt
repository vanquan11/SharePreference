package com.example.sharepreference
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/auth/credentials?format=json")
    fun getDataUser(@Query("UserName") userAc : String, @Query("Password") password : String): Call<DataModel>
}