package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models

import com.google.gson.annotations.SerializedName

data class MakeUserResponse(
        @SerializedName("result") val result: ResultMakeUser,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)