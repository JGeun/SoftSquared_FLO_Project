package jgeun.study.flo_a_jeffrey.src.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

internal class OnClearFromRecentService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}