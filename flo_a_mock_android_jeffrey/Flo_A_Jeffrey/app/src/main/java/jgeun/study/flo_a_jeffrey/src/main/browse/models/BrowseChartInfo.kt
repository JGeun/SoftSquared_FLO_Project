package jgeun.study.flo_a_jeffrey.src.main.browse.models

import com.google.gson.annotations.SerializedName

data class BrowseChartInfo(
    @SerializedName("songIdx") val songIdx: Int,
    @SerializedName("songTitle") val songTitle: String,
    @SerializedName("songImageUrl") val songImageUrl: String,
    @SerializedName("songArtist") val songArtist: String
)