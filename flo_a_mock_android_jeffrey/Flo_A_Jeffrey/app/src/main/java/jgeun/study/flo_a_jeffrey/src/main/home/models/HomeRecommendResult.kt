package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeRecommendResult(
        @SerializedName("recommendedListIdx") val recommendedListIdx: Int,
        @SerializedName("recommendedListName") val recommendedListName: String,
        @SerializedName("listInfo") val listInfo: ArrayList<HomeRecommendListInfo>
)