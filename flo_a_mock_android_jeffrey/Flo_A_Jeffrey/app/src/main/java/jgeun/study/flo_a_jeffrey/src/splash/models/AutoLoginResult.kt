package jgeun.study.flo_a_jeffrey.src.splash.models

import com.google.gson.annotations.SerializedName
import jgeun.study.flo_a_jeffrey.src.login.models.UserPlayList
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeMainPanelResult
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeRecommendResult
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeTodayResult

data class AutoLoginResult(
        @SerializedName("playlist") val playlist: ArrayList<UserPlayList>
)