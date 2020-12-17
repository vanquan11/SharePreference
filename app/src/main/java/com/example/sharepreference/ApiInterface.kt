package com.example.sharepreference
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/auth/credentials?format=json")
    fun getDataUser(@Query("UserName") userAc : String, @Query("Password") password : String): Call<DataModel>

    @GET("/api/products?format=json")
    fun getDataHH(@Header("cookie") session: String) : Call<ModelHH>

    @GET("/api/products?format=json")
    fun getData(@Header("cookie") session: String): Observable<ModelHH>


}