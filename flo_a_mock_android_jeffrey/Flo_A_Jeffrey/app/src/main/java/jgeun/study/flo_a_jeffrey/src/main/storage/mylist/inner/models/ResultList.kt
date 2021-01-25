package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models

import com.google.gson.annotations.SerializedName

data class ResultList(
        @SerializedName("listIdx") val listIdx: Int,
        @SerializedName("listName") val listName: String,
        @SerializedName("listTimestamp") val listTimestamp: String,
        @SerializedName("songCount") val songCount: Int,
        @SerializedName("listImageUrl") val listImageUrl: String,
        @SerializedName("songInfo") val songInfo: ArrayList<ResultSong>
)