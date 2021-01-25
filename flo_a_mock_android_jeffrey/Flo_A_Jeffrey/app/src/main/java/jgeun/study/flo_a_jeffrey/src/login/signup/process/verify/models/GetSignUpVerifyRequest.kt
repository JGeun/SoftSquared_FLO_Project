package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models

import com.google.gson.annotations.SerializedName

data class GetSignUpVerifyRequest (
        @SerializedName("name") val name: String,
        @SerializedName("phoneNo") val phoneNo: String
)