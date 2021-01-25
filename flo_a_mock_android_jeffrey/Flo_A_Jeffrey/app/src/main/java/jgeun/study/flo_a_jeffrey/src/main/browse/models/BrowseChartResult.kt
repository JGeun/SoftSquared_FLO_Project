package jgeun.study.flo_a_jeffrey.src.main.browse.models

import com.google.gson.annotations.SerializedName

data class BrowseChartResult (
    @SerializedName("chartIdx") val chartIdx: Int,
    @SerializedName("chartName") val chartName: String,
    @SerializedName("songInfo") val songInfo: ArrayList<BrowseChartInfo>
    )