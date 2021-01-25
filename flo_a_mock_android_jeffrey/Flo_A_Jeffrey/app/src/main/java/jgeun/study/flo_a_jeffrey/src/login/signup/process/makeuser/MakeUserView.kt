package jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser

import jgeun.study.flo_a_jeffrey.src.login.signup.process.makeuser.models.MakeUserResponse

interface MakeUserView {
    fun onPostMakeUserSuccess(response: MakeUserResponse)
    fun onPostMakeUserFailure(message: String)
}