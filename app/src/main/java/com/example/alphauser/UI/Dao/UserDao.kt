package com.example.userdataapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.userdataapp.Model.Data

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(data: List<Data>)

    @Query("SELECT * FROM user_data")
    fun getUserData(): LiveData<List<Data>>

}