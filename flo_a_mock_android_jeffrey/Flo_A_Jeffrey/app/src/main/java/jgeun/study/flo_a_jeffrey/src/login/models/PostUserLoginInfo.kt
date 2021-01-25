package jgeun.study.flo_a_jeffrey.src.login.models

import com.google.gson.annotations.SerializedName

data class PostUserLoginInfo(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String
)