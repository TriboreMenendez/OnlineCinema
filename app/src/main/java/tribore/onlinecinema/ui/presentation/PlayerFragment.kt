package tribore.onlinecinema.ui.presentation

import android.content.ComponentName
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.media3.ui.StyledPlayerView
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import tribore.onlinecinema.R
import tribore.onlinecinema.databinding.FragmentPlayerBinding
import tribore.onlinecinema.ui.player.PlaybackService

@androidx.annotation.OptIn(UnstableApi::class)
class PlayerFragment : Fragment() {
    lateinit var binding: FragmentPlayerBinding
    private lateinit var playerView: StyledPlayerView
    lateinit var sessionToken: SessionToken
    private lateinit var mediaController: ListenableFuture<MediaController>

    private val controller: MediaController?
        get() = if (mediaController.isDone) mediaController.get() else null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = binding.playerView
        hideSystemBars()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    override fun onStart() {
        super.onStart()
        initializeController()
        //playerView.player?.play()
    }

    fun pause() {
        controller?.pause()
    }

    fun play() {
        controller?.play()
    }

    // Launch player + controller binding ExoPlayer to a controller VideoView
    fun initializeController() {
        sessionToken = SessionToken(activity!!, ComponentName(activity!!, PlaybackService::class.java))

        mediaController = MediaController.Builder(
            activity!!,
            sessionToken
        ).buildAsync()


        mediaController.addListener({
            setController()
        }, MoreExecutors.directExecutor())
    }

    private fun setController() {
        val controller = this.controller ?: return

        playerView.player = controller
    }

    private fun hideSystemBars() {
        val activityWindow = activity!!.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For android version >= 11
            activityWindow.insetsController?.let {
                it.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                it.hide(WindowInsets.Type.systemBars())
            }
        }
        // For android version <11
        else @Suppress("DEPRECATION") {
            activityWindow.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            @Suppress("DEPRECATION")
            activityWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    private fun showSystemBars() {
        val activityWindow = activity!!.window
        activityWindow.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // For android version >= 11
                it.setDecorFitsSystemWindows(false)
                it.insetsController?.show(WindowInsets.Type.systemBars())
            } else @Suppress("DEPRECATION")
            // For android version <11
            it.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }
}