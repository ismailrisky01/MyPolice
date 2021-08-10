package com.example.mypolice.utils.retrofit


import com.example.mypolice.model.ModelRoot
import com.example.mypolice.model.PostModel
import retrofit2.Call
import retrofit2.http.*


interface ApiRetrofit {
 @GET("json?")
  fun getData(@Query("location")latlng:String?, @Query("radius")radius:String, @Query("type")type:String, @Query("name")name:String, @Query("key")key:String): Call<ModelRoot>


}