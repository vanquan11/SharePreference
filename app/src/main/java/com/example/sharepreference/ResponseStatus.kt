package com.example.sharepreference

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseStatus (
    @Expose
    @SerializedName("ErrorCode")
    val ErrorCode: String,
    @Expose
    @SerializedName("Message")
    val Message: String,
    @Expose
    @SerializedName("Errors")
    val Errors: ArrayErrors

)