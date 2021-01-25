package jgeun.study.flo_a_jeffrey.src.splash.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeMainPanelResult
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeRecommendResult
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeTodayResult

data class AutoLoginResponse(
        @SerializedName("result") val result: AutoLoginResult,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)