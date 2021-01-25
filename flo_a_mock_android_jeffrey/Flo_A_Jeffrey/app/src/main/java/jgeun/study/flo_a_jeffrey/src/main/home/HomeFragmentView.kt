package jgeun.study.flo_a_jeffrey.src.main.home

import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse

interface HomeFragmentView {
    fun onGetHomeInfoSuccess(response: HomeInfoResponse)
    fun onGetHomeInfoFailure(message: String)
}