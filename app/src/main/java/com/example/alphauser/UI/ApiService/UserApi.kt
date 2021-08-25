package com.example.userdataapp.ApiService

import com.example.userdataapp.Model.UserData
import retrofit2.Call
import retrofit2.http.GET


interface UserApi {

    @GET("users")
     fun getUserData() : Call<UserData>

}