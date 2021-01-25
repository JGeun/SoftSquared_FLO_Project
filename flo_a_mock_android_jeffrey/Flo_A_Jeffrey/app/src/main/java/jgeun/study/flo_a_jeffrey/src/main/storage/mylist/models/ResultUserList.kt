package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models

import com.google.gson.annotations.SerializedName

data class ResultUserList(
        @SerializedName("listIdx") val listIdx: Int,
        @SerializedName("listName") val listName: String,
        @SerializedName("characterName") val characterName: String,
        @SerializedName("characterImageUrl") val characterImageUrl: String,
        @SerializedName("listImageUrl") val listImageUrl: String,
        @SerializedName("songCount") val songCount: Int,
        @SerializedName("songInfo") val songInfo: ArrayList<ResultListMusic>
)