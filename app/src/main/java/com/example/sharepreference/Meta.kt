package com.example.sharepreference

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta (
    @Expose
    @SerializedName("PropertyName")
    val PropertyName: String
)