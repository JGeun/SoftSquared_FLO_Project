package jgeun.study.flo_a_jeffrey.src.foreground

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.icu.number.Scale
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.UserPlayList
import jgeun.study.flo_a_jeffrey.src.service.NotificationActionService
import java.io.InputStream
import java.net.URL


object CreateNotification {
    const val CHANNEL_ID = "channel1"
    const val ACTION_PREVIOUS = "actionprevious"
    const val ACTION_PLAY = "actionplay"
    const val ACTION_NEXT = "actionnext"
    lateinit var notification: Notification

    fun createNotification(context: Context, track: UserPlayList, playbutton: Int, pos: Int, size: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, "tag")
           // val encodeByte = Base64.decode(track.songImageUrl, Base64.DEFAULT)
            //var icon = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            var icon = BitmapFactory.decodeStream((URL(track.songImageUrl).getContent() as InputStream))
            //icon = Bitmap.createScaledBitmap(icon!!, 512, 512, false)
            var pendingIntentPrevious: PendingIntent?
            var drw_previous: Int
            if (pos == 0) {
                pendingIntentPrevious = null
                //drw_previous = 0
            }else{
                val intentPrevious = Intent(context, NotificationActionService::class.java)
                        .setAction(ACTION_PREVIOUS)
                pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT)
                //drw_previous = R.drawable.ic_baseline_skip_previous_24
            }
            drw_previous = R.drawable.ic_notification_previous


            val intentPlay = Intent(context, NotificationActionService::class.java)
                    .setAction(ACTION_PLAY)
            val pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay,
                    PendingIntent.FLAG_UPDATE_CURRENT)
            var pendingIntentNext: PendingIntent?
            var drw_next: Int
            if (pos == size) {
                pendingIntentNext = null
                drw_next = 0
            }
                val intentNext = Intent(context, NotificationActionService::class.java)
                        .setAction(ACTION_NEXT)
                pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT)
                drw_next = R.drawable.ic_notification_next

            //create notification
            notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification_small)
                    .setContentTitle(track.songTitle)
                    .setContentText(track.artistName)
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true) //show notification for only first time
                    .addAction(drw_previous, "Previous", pendingIntentPrevious)
                    .addAction(playbutton, "Play", pendingIntentPlay)
                    .addAction(drw_next, "Next", pendingIntentNext)
                    .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1, 2)) //                   .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                    //                            .setShowActionsInCompactView(0, 1, 2)
                    //                            .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build()
            notificationManagerCompat.notify(1, notification)
        }
    }
}
