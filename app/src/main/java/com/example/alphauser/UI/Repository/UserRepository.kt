package com.example.userdataapp.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.userdataapp.ApiService.UserApi
import com.example.userdataapp.ApiService.UserApiInstance
import com.example.userdataapp.Database.UserDatabase
import com.example.userdataapp.Model.Data
import com.example.userdataapp.Model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class UserRepository {

    companion object{

        var userDatabase:UserDatabase? = null

        private fun initialiseDB(context: Context): UserDatabase?{
            return UserDatabase.getInstance(context)
        }

        fun insert(context: Context,data: List<Data>){
            userDatabase = initialiseDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase?.userDao?.insertUserData(data)
            }
        }

        fun getUserDataFromDB(context: Context): LiveData<List<Data>>? {
            userDatabase = initialiseDB(context)
            return userDatabase?.userDao?.getUserData()
        }

    }
}