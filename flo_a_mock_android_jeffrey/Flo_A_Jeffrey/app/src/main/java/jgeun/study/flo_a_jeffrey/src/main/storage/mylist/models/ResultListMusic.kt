package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models

import com.google.gson.annotations.SerializedName

data class ResultListMusic(
        @SerializedName("songIdx") val songIdx: Int,
        @SerializedName("songTitle") val songTitle: String,
        @SerializedName("artistName") val artistName: String,
        @SerializedName("songImageUrl") val songImageUrl: String
)