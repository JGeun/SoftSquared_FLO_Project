package jgeun.study.flo_a_jeffrey.src.login

import jgeun.study.flo_a_jeffrey.src.login.models.PostUserLoginResponse

interface LoginView {
    fun onGetLoginInfoSuccess(respose: PostUserLoginResponse)
    fun onGetUserInfoFailure(message: String)
}