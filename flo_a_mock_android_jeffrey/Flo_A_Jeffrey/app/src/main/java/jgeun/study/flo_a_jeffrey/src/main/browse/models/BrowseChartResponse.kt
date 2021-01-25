package jgeun.study.flo_a_jeffrey.src.main.browse.models

import com.google.gson.annotations.SerializedName

data class BrowseChartResponse (
    @SerializedName("result") val result: ArrayList<BrowseChartResult>,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
    )