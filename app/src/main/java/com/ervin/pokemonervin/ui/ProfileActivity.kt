package com.ervin.pokemonervin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ervin.pokemonervin.R
import com.ervin.pokemonervin.utils.loadCircleImagesFromFile
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        iv_profile_picture.loadCircleImagesFromFile(this, "file:///android_asset/Ervin.jpg")
    }
}
