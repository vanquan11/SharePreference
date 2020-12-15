package com.example.sharepreference

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArrayErrors (
    @Expose
    @SerializedName("ErrorCode")
    val ErrorCode: String,
    @Expose
    @SerializedName("FieldName")
    val FieldName: String,
    @Expose
    @SerializedName("Message")
    val Message: String,
    @Expose
    @SerializedName("Meta")
    val Meta: Meta
){

}