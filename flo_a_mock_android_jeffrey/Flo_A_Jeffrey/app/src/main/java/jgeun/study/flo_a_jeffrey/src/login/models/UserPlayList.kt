package jgeun.study.flo_a_jeffrey.src.login.models

import com.google.gson.annotations.SerializedName

data class UserPlayList (
        @SerializedName("songIdx") val songIdx: Int,
        @SerializedName("songTitle") val songTitle: String,
        @SerializedName("songImageUrl") val songImageUrl: String,
        @SerializedName("artistName") val artistName: String
        )