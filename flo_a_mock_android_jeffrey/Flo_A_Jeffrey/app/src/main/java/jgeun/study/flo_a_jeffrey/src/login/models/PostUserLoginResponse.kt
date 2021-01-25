package jgeun.study.flo_a_jeffrey.src.login.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.ResultMakeUser

data class PostUserLoginResponse (
        @SerializedName("result") val result: UserToken,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)