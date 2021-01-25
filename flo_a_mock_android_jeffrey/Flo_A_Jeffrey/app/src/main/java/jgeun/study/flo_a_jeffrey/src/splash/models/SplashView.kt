package jgeun.study.flo_a_jeffrey.src.splash.models

interface SplashView {
        fun onGetAutoLoginSuccess(response: AutoLoginResponse)
        fun onGetAutoLoginFailure(message: String)
}