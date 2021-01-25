package jgeun.study.flo_a_jeffrey.src.main

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityMainBinding
import jgeun.study.flo_a_jeffrey.src.main.browse.BrowseFragment
import jgeun.study.flo_a_jeffrey.src.main.home.HomeFragment
import jgeun.study.flo_a_jeffrey.src.main.search.MusicSearchFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.StorageFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.InnerListFragment
import jgeun.study.flo_a_jeffrey.src.music.MusicPlayerActivity
import jgeun.study.flo_a_jeffrey.src.music.MusicView
import jgeun.study.flo_a_jeffrey.src.music.list.MusicListActivity
import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import jgeun.study.flo_a_jeffrey.src.music.songinfo.SongInfoFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.mainStatusBarColor, null)

        if(ApplicationClass.userMusicPlayList.size == 0){
            binding.mainLlMusicPlayerNoMusic.visibility = View.VISIBLE
            binding.mainLlMusicPlayer.visibility = View.INVISIBLE
        } else {
            binding.mainLlMusicPlayerNoMusic.visibility = View.INVISIBLE
            binding.mainLlMusicPlayer.visibility = View.VISIBLE
            binding.mainTvSongTitle.setText(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songTitle)
            binding.mainTvArtistName.setText(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].artistName)


            val playButton = binding.mainIvSongPlay
            if(ApplicationClass.mediaPlayer.isPlaying){
                playButton.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
                playButton.setTag(2)
            }else{
                playButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_list_play, null))
                playButton.setTag(1)
            }
            /*
            playButton.setOnClickListener{
               if(playButton.getTag() == 1){
                   playButton.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
                   playButton.setTag(2)
               }else{
                   playButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_list_play, null))
                   playButton.setTag(1)
               }
            }*/
        }

        binding.mainRlMusicPlayerInfo.setOnClickListener{
            val startMusicPlayerIntent = Intent(this, MusicPlayerActivity::class.java)
            startMusicPlayerIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(startMusicPlayerIntent)
            finish()
        }

        val musicPath = "https://product.jun-softsquared.shop/spring.mp3"
        val url = musicPath

        val playBtn = binding.mainIvSongPlay
        playBtn.setOnClickListener{
            if(playBtn.getTag().equals("1")){
                if (ApplicationClass.pausePosition == -1) {
                    ApplicationClass.mediaPlayer = MediaPlayer().apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(url)
                        prepare()
                        start()
                        ApplicationClass.pausePosition = 0
                    }

                    ApplicationClass.mediaPlayer.setOnCompletionListener {
                        playBtn.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
                        playBtn.setPadding(0, 0, 0, 0)
                        ApplicationClass.isPlaying = false
                        ApplicationClass.pausePosition = -1
                        ApplicationClass.mediaPlayer.release()
                        playBtn.setTag("1")
                    }
                }
                playBtn.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
                playBtn.setPadding(0,0, 10,0)
                playBtn.setTag("2")
                ApplicationClass.mediaPlayer.seekTo(ApplicationClass.pausePosition)
                ApplicationClass.mediaPlayer.start()
                ApplicationClass.isPlaying = true
            }else{
                playBtn.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_list_play, null))
                playBtn.setPadding(0,0, 0, 0)
                playBtn.setTag("1")
                ApplicationClass.pausePosition = ApplicationClass.mediaPlayer.currentPosition
                ApplicationClass.mediaPlayer.pause()
                ApplicationClass.isPlaying = false
            }
        }


        binding.mainRlSongList.setOnClickListener{
            val startMusicListIntent = Intent(this, MusicListActivity::class.java)
            startMusicListIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivityForResult(startMusicListIntent, 1000)
        }

        val listIdx = intent.getIntExtra("listIdx", -1)
        if(intent.getStringExtra("MusicSetting").toString().equals("MusicSetting")){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, SongInfoFragment())
                    .commitAllowingStateLoss()
        }else if(listIdx != -1){
            supportFragmentManager.beginTransaction().replace(R.id.main_frm, InnerListFragment(listIdx)).commitAllowingStateLoss()
        }else
            supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        Log.d("MusicSetting", intent.getStringExtra("MusicSetting").toString())

        binding.mainBtmNav.itemIconTintList = null
        binding.mainBtmNav.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.menu_main_btm_nav_home -> {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, HomeFragment())
                                    .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_my_page -> {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, BrowseFragment())
                                    .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_search -> {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, MusicSearchFragment(this))
                                    .commitAllowingStateLoss()

                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_storage -> {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, StorageFragment())
                                    .commitAllowingStateLoss()

                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                })
    }

    fun checkMusicPlayList(){
        if(ApplicationClass.userMusicPlayList.size == 0){
            binding.mainLlSongInfo.visibility = View.INVISIBLE
            binding.mainLlMusicPlayerNoMusic.visibility = View.VISIBLE
        }else{
            binding.mainLlSongInfo.visibility = View.VISIBLE
            binding.mainLlMusicPlayer.visibility = View.INVISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    fun statusBarHeight(context: Context): Int{
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        return if(resourceId > 0 ) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

}
