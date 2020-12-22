package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import com.example.sharepreference.PreferenceHelper.sessionId
import com.example.sharepreference.PreferenceHelper.userId
import com.example.sharepreference.PreferenceHelper.userName

import kotlinx.android.synthetic.main.activity_data_preference.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val CUSTOM_PREF_NAME = "User_data"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener(this)

        val sharePrefs = PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
        edtAc.setText(sharePrefs.userName.toString())

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                val user: String = edtAc.text.toString()
                val password: String = edtPw.text.toString()

                getData(user, password)
            }

        }
    }

    private fun getData(userAc: String, password: String) {
        val prefs = PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
        APIClient.getClient.getDataUser(userAc, password).enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                if (response != null && response.isSuccessful) {
                    val dataModel: DataModel = response.body() ?: DataModel(responseStatus = "")
                    prefs.userId = dataModel.userId
                    prefs.sessionId = dataModel.sessionId
                    prefs.userName = dataModel.userName

                    intent = Intent(this@MainActivity, DataPreferenceActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("key", response.body() ?: DataModel(responseStatus = ""))
                    intent.putExtra("bundle", bundle)
                    startActivity(intent)
                } else {
                    Log.e("vq","errorAccount ${response?.message()}")
                }
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
            }

        })

    }
}