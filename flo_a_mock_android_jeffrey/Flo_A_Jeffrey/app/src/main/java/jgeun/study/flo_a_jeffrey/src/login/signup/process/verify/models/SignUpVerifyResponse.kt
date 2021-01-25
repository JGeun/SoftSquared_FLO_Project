package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models

import com.google.gson.annotations.SerializedName

data class SignUpVerifyResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)