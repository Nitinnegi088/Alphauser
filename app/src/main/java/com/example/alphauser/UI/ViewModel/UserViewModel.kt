package com.example.userdataapp.ViewModel


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userdataapp.Model.Data
import com.example.userdataapp.Model.UserData
import com.example.userdataapp.Repository.UserRepository

class UserViewModel : ViewModel ()  {

    fun insert(context: Context,data: List<Data>){
        UserRepository.insert(context,data)
    }

    fun getUserDataFromDB(context: Context): LiveData<List<Data>>?{
        Log.d("getroomdata", UserRepository.userDatabase?.userDao?.getUserData().toString())
        return UserRepository.getUserDataFromDB(context)
    }
}