package jgeun.study.flo_a_jeffrey.src.login.models

import com.google.gson.annotations.SerializedName

data class UserToken(
        @SerializedName("jwt") val jwt: String,
        @SerializedName("playlist") val playlist: ArrayList<UserPlayList>
        )

