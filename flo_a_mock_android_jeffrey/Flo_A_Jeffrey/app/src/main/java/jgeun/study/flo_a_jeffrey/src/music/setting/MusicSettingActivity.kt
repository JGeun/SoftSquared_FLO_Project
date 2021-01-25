package jgeun.study.flo_a_jeffrey.src.music.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityMusicSettingBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity

class MusicSettingActivity : BaseActivity<ActivityMusicSettingBinding>(ActivityMusicSettingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val songIdx = intent.getIntExtra("songIdx", -1)
        Log.d("SettingActivity(SongIdx)", songIdx.toString())
        val position = intent.getIntExtra("position", -1)
        Log.d("SettingActivity(Position)", position.toString())
        binding.musicSettingLlSongInfo.setOnClickListener{
            val showMusicSettingIntent = Intent(this, MainActivity::class.java)
            showMusicSettingIntent.putExtra("MusicSetting", "MusicSetting")
            showMusicSettingIntent.putExtra("SongIdx", songIdx)
            startActivity(showMusicSettingIntent)
            finish()
        }

        binding.musicSettingIvExit.setOnClickListener{
            finish()
        }
        binding.musicSettingLlDelete.setOnClickListener{
            if(position != -1){
                ApplicationClass.userMusicPlayList.removeAt(position)
                if(ApplicationClass.userMusicPlayList.size == 0) {
                    ApplicationClass.musicPlayIndex = -1
                }else if(ApplicationClass.userMusicPlayList.size == position){
                    ApplicationClass.musicPlayIndex = ApplicationClass.userMusicPlayList.size - 1
                }
                setResult(RESULT_OK)

                finish()
                overridePendingTransition(0, 0);
            }
        }
    }
}