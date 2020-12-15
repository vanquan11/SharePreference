package com.example.sharepreference
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DataModel(
    @SerializedName("DisplayName") var displayName: String = "",
    @SerializedName("Permissions") var permissions: List<Any> = listOf(),
    @SerializedName("ProfileUrl") var profileUrl: String = "",
    @SerializedName("ResponseStatus") var responseStatus: Any ,
    @SerializedName("Roles") var roles: List<String> = listOf(),
    @SerializedName("SessionId") var sessionId: String = "",
    @SerializedName("UserId") var userId: String = "",
    @SerializedName("UserName") var userName: String = ""
)
