package sk.upjs.myapplication

import android.app.Service
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.IBinder
//https://stackoverflow.com/questions/74910453/play-audio-file-in-the-background-using-a-service
class BackgroundSoundService : Service() {
    private lateinit var player: MediaPlayer

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val afd: AssetFileDescriptor = applicationContext.assets.openFd("hudbapozadia.wav")
        player = MediaPlayer().apply {
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            isLooping = true // Set looping
            setVolume(1.0f, 1.0f)
            prepare()
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }
}
