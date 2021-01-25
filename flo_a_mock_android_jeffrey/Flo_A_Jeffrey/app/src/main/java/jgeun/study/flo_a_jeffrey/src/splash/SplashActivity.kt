package jgeun.study.flo_a_jeffrey.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.res.ResourcesCompat
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.config.UserPlayList
import jgeun.study.flo_a_jeffrey.databinding.ActivitySplashBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.splash.models.AutoLoginResponse
import jgeun.study.flo_a_jeffrey.src.splash.models.SplashView

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.white, null)
        SplashService(this).tryGetAutoLogin()
    }

    override fun onGetAutoLoginSuccess(response: AutoLoginResponse) {
        if(response.code == 1000){
            val playListArray = response.result.playlist
            for(playList in playListArray){
                ApplicationClass.userMusicPlayList.add(UserPlayList(playList.songIdx, playList.songTitle, playList.songImageUrl,playList.artistName))
            }
        }else{
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString(ApplicationClass.X_ACCESS_TOKEN, null)
            editor.apply()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }

    override fun onGetAutoLoginFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}