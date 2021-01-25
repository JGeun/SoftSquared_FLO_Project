package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeRecommendListInfo(
        @SerializedName("listIdx") val listIdx: Int,
        @SerializedName("listName") val listName: String,
        @SerializedName("listImageUrl") val listImageUrl: String,
        @SerializedName("songInfo") val songInfo: ArrayList<HomeRecommendSongInfo>
)