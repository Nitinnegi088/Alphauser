package com.example.userdataapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class Data(

        @ColumnInfo(name = "user_images")
        var avatar: String,

        @ColumnInfo(name = "email")
        var email: String,

        @ColumnInfo(name = "f_name")
        var first_name: String,

        @PrimaryKey()
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "l_name")
        var last_name: String
)