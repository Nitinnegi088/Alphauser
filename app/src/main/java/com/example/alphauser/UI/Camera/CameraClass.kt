@file:Suppress("DEPRECATION")

package com.example.alphauser.UI.Camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.alphauser.R

class CameraClass :AppCompatActivity() {

    private var RESULT_CODE = 100
    private var RESULT_CODE_VIDEO = 101
    private lateinit var image: ImageView
    private lateinit var video_view: VideoView
    private lateinit var capture: Button
    private var video:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_layout)
        image = findViewById(R.id.image)
        video_view = findViewById(R.id.video)
        capture = findViewById(R.id.capture)
        video = intent.getBooleanExtra("video",false)
        if (video){
            image.isGone = true
            video_view.isVisible = true
            capture.text = "Capture Video"
        }
    }

    fun takePhoto(view: View){
        if (video){
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, RESULT_CODE_VIDEO)
            }
        }else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, RESULT_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE && resultCode == RESULT_OK && data != null) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(photo)
        }else if(requestCode == RESULT_CODE_VIDEO && resultCode == RESULT_OK && data != null){
            video_view.setVideoURI(data?.data)
            video_view.start()
        }
    }
}