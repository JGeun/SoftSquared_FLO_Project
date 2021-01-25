package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeMainPanelResult (
        @SerializedName("listIdx") val listIdx: Int,
        @SerializedName("listName") val listName: String,
        @SerializedName("listTimestamp") val listTimestamp: String,
        @SerializedName("songCount") val songCount: Int,
        @SerializedName("songInfo") val songInfo: ArrayList<HomeSongInfo>
)