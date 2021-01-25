package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models

import com.google.gson.annotations.SerializedName

data class PostMakeUserRequest (
        @SerializedName("name") val name: String,
        @SerializedName("phoneNo") val phoneNo: String,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("dateOfBirth") val dateOfBirth: String
)