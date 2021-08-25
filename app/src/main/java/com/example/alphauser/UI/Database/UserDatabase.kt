package com.example.userdataapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userdataapp.Dao.UserDao
import com.example.userdataapp.Model.Data

@Database(entities = [Data::class],version = 1,exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion  object{

        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            var instance:UserDatabase? = INSTANCE
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                    if (instance == null){
                        instance = Room.databaseBuilder(context.applicationContext,
                            UserDatabase::class.java,"user_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                INSTANCE = instance
                return INSTANCE
            }
        }
    }
}