package com.example.userdataapp.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiInstance {

        companion object{

            private val base_Url= "https://reqres.in/api/"

            fun getUserApiInstance(): Retrofit{
                    return Retrofit.Builder().
                    baseUrl(base_Url).
                    addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
}