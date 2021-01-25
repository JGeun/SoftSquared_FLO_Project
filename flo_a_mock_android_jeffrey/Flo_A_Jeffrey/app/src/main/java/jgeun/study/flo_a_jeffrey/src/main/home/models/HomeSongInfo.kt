package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeSongInfo(
        @SerializedName("songIdx") val songIdx: Int,
        @SerializedName("songTitle") val songTitle: String,
        @SerializedName("artistName") val artistName: String,
        @SerializedName("songImageUrl") val songImageUrl: String,
        @SerializedName("artistImageUrl") val artistImageUrl: String
)