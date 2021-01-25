package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models

import com.google.gson.annotations.SerializedName

data class ResultSong(
        @SerializedName("songIdx") val songIdx: Int,
        @SerializedName("songTitle") val songTitle: String,
        @SerializedName("songImageUrl") val songImageUrl: String,
        @SerializedName("artistName") val artistName: String
)