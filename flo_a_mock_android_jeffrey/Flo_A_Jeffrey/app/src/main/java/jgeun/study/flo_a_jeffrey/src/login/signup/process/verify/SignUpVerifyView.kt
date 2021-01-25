package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify

import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.SignUpVerifyResponse

interface SignUpVerifyView {
    fun onGetSignUpVerifySuccess(response: SignUpVerifyResponse)
    fun onGetSignUpVerifyFailure(message: String)
}