package com.example.sharepreference
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("api/auth/credentials?format=json")
    fun getDataUser(@Query("UserName") userAc : String, @Query("Password") password : String): Call<DataModel>

    @GET("api/products?format=json")
    fun getData(@Header("cookie") session: String): Observable<ModelHH>

    @DELETE("api/products/{id}")
    fun deleteData(@Header("cookie") session: String, @Path("id") id : Int) : Completable

    @POST("api/products?format=json")
    fun postData(@Header("cookie") session: String, @Body merchandise: ProductToPost) : Observable<ObResponsPost>

    @POST("api/products?format=json")
    fun putData(@Header("cookie") session: String, @Body merchandise: ProductToPost) : Observable<ObResponsPut>

}