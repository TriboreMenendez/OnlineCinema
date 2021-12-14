package tribore.onlinecinema.ui.player

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import java.io.IOException

// Так как библиотека media3 пока в альфе, она считается нестабильной и требует анотации
@androidx.annotation.OptIn(UnstableApi::class)
class PlaybackService : MediaSessionService() {

    private lateinit var mediaSession: MediaSession
    lateinit var player: ExoPlayer

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onCreate() {
        super.onCreate()
        initializeSessionAndPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        mediaSession.release()
    }

    private fun initializeSessionAndPlayer() {
        player = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, player).build()
        playCinema()
    }

    fun releasePlayer() {
        player.release()
    }



    private fun playCinema() {
        player.apply {
            setMediaItem(MediaItem.fromUri(CinemaItem.getItem()))
            playWhenReady = playWhenReady
            prepare()
            play()
        }
    }
}