package com.example.alphauser.UI.Ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alphauser.R


class ProfileDetails : AppCompatActivity() {

    private lateinit var pImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var userIdtextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_details_activity)
        initViews()
        setData()
    }

    private fun initViews() {
        pImageView = findViewById(R.id.user_image_detail)
        nameTextView = findViewById(R.id.user_name_detail)
        emailTextView = findViewById(R.id.user_Email_deatil)
        userIdtextView = findViewById(R.id.userID_detail)
    }

    private fun setData() {
        val profileUserId = intent.getStringExtra("ProfileUserId")
        val profileImage =intent.getStringExtra("ProfileImage")
        val profileName = intent.getStringExtra("profileName")
        val profileEmail = intent.getStringExtra("profileEmail")
        Glide.with(this).load(profileImage).into(pImageView)
        nameTextView.text = profileName
        emailTextView.text = profileEmail
        userIdtextView.text = profileUserId
    }
}