package jgeun.study.flo_a_jeffrey.src.music

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.Typeface
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.mediaPlayer
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.config.UserPlayDetail
import jgeun.study.flo_a_jeffrey.databinding.ActivityMusicPlayerBinding
import jgeun.study.flo_a_jeffrey.src.foreground.CreateNotification
import jgeun.study.flo_a_jeffrey.src.foreground.Playable
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.music.list.MusicListActivity
import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import jgeun.study.flo_a_jeffrey.src.music.recommend.RecommendMusicActivity
import jgeun.study.flo_a_jeffrey.src.music.setting.MusicSettingActivity
import jgeun.study.flo_a_jeffrey.src.service.OnClearFromRecentService
import kotlin.math.min

class MusicPlayerActivity : BaseActivity<ActivityMusicPlayerBinding>(ActivityMusicPlayerBinding::inflate), MusicView, Playable {
    private lateinit var musicPlayButton: ImageView
    private lateinit var songLyrics: List<String>
    private var songIdx = ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songIdx
    lateinit var notificationManager: NotificationManager

    var lyricsUp =  ArrayList<String>()
    lateinit var seekBar: SeekBar
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACKS"))
            startService(Intent(baseContext, OnClearFromRecentService::class.java))
        }

        setLyricsUp()

        if(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songIdx != 1){
            binding.musicListCvSubAlbum.visibility = View.INVISIBLE
        }

        seekBar = findViewById(R.id.musicPlayer_seekbar)
        Glide.with(this).load(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songImageUrl).into(binding.musicPlayerIvAlbum)
        binding.musicPlayerIvAlbum.background = ResourcesCompat.getDrawable(resources, R.drawable.music_player_album_background, null)
        binding.musicPlayerIvAlbum.clipToOutline = true

        binding.musicPlayerTvSongTitle.setText(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songTitle)
        binding.musicPlayerTvSinger.setText(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].artistName)

        if (!ApplicationClass.isHaveDetailInfo) {
            MusicService(this).tryGetMusicInfo(songIdx)
        } else {
            binding.musicListTvEndTime.setText(ApplicationClass.musicDetail.songLength)
        }

        musicPlayButton = binding.musicPlayerIvPlay

        if (ApplicationClass.isPlaying) {
            musicPlayButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
            musicPlayButton.setTag("2")
            musicPlayButton.setPadding(0, 0, 0, 0)
        } else {
            musicPlayButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
            musicPlayButton.setTag("1")
            musicPlayButton.setPadding(15, 0, 0, 0)
        }

        musicPlayButton.setOnClickListener {
            if (musicPlayButton.getTag().equals("1")) {
                if (ApplicationClass.pausePosition == -1) {
                    ApplicationClass.mediaPlayer.setOnCompletionListener {
                        musicPlayButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
                        musicPlayButton.setPadding(15, 0, 0, 0)
                        ApplicationClass.isPlaying = false
                        ApplicationClass.pausePosition = -1
                        mediaPlayer.release()
                        musicPlayButton.setTag("1")
                    }
                }
                musicPlayButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
                musicPlayButton.setPadding(0, 0, 0, 0)
                musicPlayButton.setTag("2")
                onTrackPlay()

            } else {
                musicPlayButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
                musicPlayButton.setPadding(15, 0, 0, 0)
                musicPlayButton.setTag("1")
                onTrackPause()
            }
        }

        binding.musicPlayerIvSongSetting.setOnClickListener {
            startActivity(Intent(this, MusicSettingActivity::class.java))
        }

        binding.musicPlayerIvPlayerDown.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            overridePendingTransition(0, 0)
        }

        binding.musicPlayerIvSimilar.setOnClickListener {
            startActivity(Intent(this, RecommendMusicActivity::class.java))
        }

        binding.musicPlayerIvSongList.setOnClickListener {
            startActivity(Intent(this, MusicListActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        overridePendingTransition(0, 0)
        super.onBackPressed()
    }

    override fun onGetMusicInfoSuccess(response: MusicResponse) {
        if (response.code == 1000 || response.code == 1001) {
            binding.musicListTvEndTime.setText(response.result.songLength)
            ApplicationClass.musicDetail = UserPlayDetail(response.result.songContents, response.result.songLyrics, response.result.songLength)
            songLyrics = response.result.songLyrics.split("\n")
            binding.musicPlayerTvLyrics1.setText(songLyrics[0])
            binding.musicPlayerTvLyrics2.setText(songLyrics[1])
            ApplicationClass.musicUrl = response.result.songContents

            mediaPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(response.result.songContents)
                prepare()
                ApplicationClass.pausePosition = 0
            }
            val songLength = response.result.songLength
            seekBar.setProgress(0)
            seekBar.max = mediaPlayer.duration

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    val seek = seekBar!!.progress
                    mediaPlayer.seekTo(seek)
                    mediaPlayer.start()
                    MyThead().start()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    mediaPlayer.pause()
                }

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(seekBar!!.max == progress){
                        mediaPlayer.stop()
                    }
                }
            })

        }
    }

    inner class MyThead : Thread() {
        override fun run() {
            while (mediaPlayer.isPlaying) {
                seekBar.setProgress(mediaPlayer.currentPosition)
                handler.post(){
                    var timeInt = mediaPlayer.currentPosition/1000
                    var time : String = ""
                    val minute = timeInt/60
                    if(minute < 10)
                        time += "0"
                    time += minute.toString()
                    time += ":"
                    val second = timeInt % 60
                    if(second < 10)
                        time += "0"
                    time += second.toString()

                    if(time.equals(lyricsUp.get(0))){
                        binding.musicPlayerTvLyrics1.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
                        binding.musicPlayerTvLyrics1.setTypeface(binding.musicPlayerTvLyrics1.getTypeface(), Typeface.BOLD)
                    }else{
                        var isFindLyrics = false
                        for(i in 1 until lyricsUp.size){
                            if(lyricsUp.get(i).equals(time)){
                                binding.musicPlayerTvLyrics1.setText(songLyrics[i])
                                binding.musicPlayerTvLyrics2.setText(songLyrics[i+1])
                                isFindLyrics = true
                                break
                            }
                        }
                    }
                    binding.musicListTvStartTime.setText(time)
                }

            }
        }
    }

    override fun onGetMusicInfoFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CreateNotification.CHANNEL_ID,
                    "KOD Dev", NotificationManager.IMPORTANCE_LOW)
            notificationManager = getSystemService(NotificationManager::class.java)
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.extras!!.getString("actionname")
            when (action) {
                CreateNotification.ACTION_PREVIOUS -> onTrackPrevious()
                CreateNotification.ACTION_PLAY -> if (ApplicationClass.isPlaying) {
                    onTrackPause()
                } else {
                    onTrackPlay()
                }
                CreateNotification.ACTION_NEXT -> onTrackNext()
            }
        }
    }

    override fun onTrackPrevious() {
        ApplicationClass.musicPlayIndex--
        CreateNotification.createNotification(this, ApplicationClass.userMusicPlayList.get(ApplicationClass.musicPlayIndex),
                R.drawable.ic_notification_pause, ApplicationClass.musicPlayIndex, ApplicationClass.userMusicPlayList.size - 1)
    }

    override fun onTrackPlay() {
        CreateNotification.createNotification(this, ApplicationClass.userMusicPlayList.get(ApplicationClass.musicPlayIndex),
                R.drawable.ic_notification_pause, ApplicationClass.musicPlayIndex, ApplicationClass.userMusicPlayList.size - 1)
        binding.musicPlayerIvPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
        binding.musicPlayerIvPlay.setPadding(0, 0, 0, 0)
        ApplicationClass.isPlaying = true
        mediaPlayer.seekTo(ApplicationClass.pausePosition)
        mediaPlayer.start()
        MyThead().start()
    }

    override fun onTrackPause() {
        CreateNotification.createNotification(this, ApplicationClass.userMusicPlayList.get(ApplicationClass.musicPlayIndex),
                R.drawable.ic_notification_play, ApplicationClass.musicPlayIndex, ApplicationClass.userMusicPlayList.size - 1)
        binding.musicPlayerIvPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
        binding.musicPlayerIvPlay.setPadding(15, 0, 0, 0)
        ApplicationClass.isPlaying = false
        ApplicationClass.pausePosition = mediaPlayer.currentPosition
        mediaPlayer.pause()
    }

    override fun onTrackNext() {
        ApplicationClass.musicPlayIndex++
        CreateNotification.createNotification(this, ApplicationClass.userMusicPlayList.get(ApplicationClass.musicPlayIndex),
                R.drawable.ic_notification_pause, ApplicationClass.musicPlayIndex, ApplicationClass.userMusicPlayList.size - 1)
    }

    fun setLyricsUp(){
        lyricsUp.add("00:18")
        lyricsUp.add("00:21")
        lyricsUp.add("00:24")
        lyricsUp.add("00:27")
        lyricsUp.add("00:30")
        lyricsUp.add("00:33")
        lyricsUp.add("00:36")
        lyricsUp.add("00:39")
        lyricsUp.add("00:43")
        lyricsUp.add("00:48")
        lyricsUp.add("00:51")
        lyricsUp.add("00:54")
        lyricsUp.add("00:58")
    }
}