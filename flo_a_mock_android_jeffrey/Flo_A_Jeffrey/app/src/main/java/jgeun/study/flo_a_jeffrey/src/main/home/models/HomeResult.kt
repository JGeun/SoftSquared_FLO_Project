package jgeun.study.flo_a_jeffrey.src.main.home.models

import com.google.gson.annotations.SerializedName

data class HomeResult(
        @SerializedName("mainResult") val mainResult: ArrayList<HomeMainPanelResult>,
        @SerializedName("todayReleasedMusic") val todayReleasedMusic: ArrayList<HomeTodayResult>,
        @SerializedName("recommendedLists") val recommendedLists: ArrayList<HomeRecommendResult>
)