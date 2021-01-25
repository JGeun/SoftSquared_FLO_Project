package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeTodayResult(
        @SerializedName("albumIdx") val albumIdx: Int,
        @SerializedName("albumTitle") val albumTitle: String,
        @SerializedName("albumImageUrl") val albumImageUrl: String,
        @SerializedName("artistName") val artistName: String
)