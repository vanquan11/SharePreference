package com.example.sharepreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_preference.*

class DataPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_preference)


        val bundle: Bundle? = intent.extras
        val userId = bundle?.get("userId")
        val sessionId = bundle?.get("sessionId")
        val userName = bundle?.get("userName")

        txtUserId.setText(userId.toString())
        txtSessionId.setText(sessionId.toString())
        txtUserName.setText(userName.toString())
    }
}