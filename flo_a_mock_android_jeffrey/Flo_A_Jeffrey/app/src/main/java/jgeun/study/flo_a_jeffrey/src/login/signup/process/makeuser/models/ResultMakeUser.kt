package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models

import com.google.gson.annotations.SerializedName

data class ResultMakeUser (
        @SerializedName("jwt") val jwt: String
)