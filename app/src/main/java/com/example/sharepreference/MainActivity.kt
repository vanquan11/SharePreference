package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.sharepreference.MainActivity.PreferenceHelper.customPreference
import com.example.sharepreference.MainActivity.PreferenceHelper.sessionId
import com.example.sharepreference.MainActivity.PreferenceHelper.userId
import com.example.sharepreference.MainActivity.PreferenceHelper.userName
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
        val sharePrefs = customPreference(this, CUSTOM_PREF_NAME)
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
        val prefs = customPreference(this, CUSTOM_PREF_NAME)
        APIClient.getClient.getDataUser(userAc, password).enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                var dataModel : DataModel
                dataModel = response!!.body()
                prefs.userId = dataModel.userId
                prefs.sessionId = dataModel.sessionId
                prefs.userName = dataModel.userName
                intent = Intent(this@MainActivity, DataPreferenceActivity::class.java)
                intent.putExtra("userId", prefs.userId)
                intent.putExtra("sessionId", prefs.sessionId)
                intent.putExtra("userName", prefs.userName)
                startActivity(intent)
                Log.d("vq", "" + prefs.userId + "," + prefs.sessionId + "," + prefs.userName)
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                Log.d("vq", "" + t.toString())
            }

        })

    }
    object PreferenceHelper {

        val USER_ID = "USER_ID"
        val SESSION_ID = "SESSIONID"
        val USER_NAME = "USERNAME"

        fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
            val editMe = edit()
            operation(editMe)
            editMe.apply()
        }

        inline fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
            val key = pair.first
            val value = pair.second
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> error("Only primitive types can be stored in SharedPreferences")
            }
        }

        var SharedPreferences.userId
            get() = getString(USER_ID, "")
            set(value) {
                editMe {
                    it.putString(USER_ID, value)
                }
            }

        var SharedPreferences.sessionId
            get() = getString(SESSION_ID, "")
            set(value) {
                editMe {
                    it.putString(SESSION_ID, value)
                }
            }
        var SharedPreferences.userName
            get() = getString(USER_NAME, "")
            set(value) {
                editMe {
                    it.putString(USER_NAME, value)
                }
            }

        var SharedPreferences.clearValues
            get() = { }
            set(value) {
                editMe {
                    it.clear()
                }
            }
    }
}