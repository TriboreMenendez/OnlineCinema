package tribore.onlinecinema.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tribore.onlinecinema.data.network.CinemaNetwork
import java.io.IOException

class MyViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getCinemaAll()
    }

    private fun getCinemaAll() {
        viewModelScope.launch {
            try {
                val playlist = CinemaNetwork.retrofitService.getPlaylist()
                _status.value = playlist.results[0].posterPath

            } catch (networkError: IOException) {
                _status.value = "Fail"
            }
        }
    }
}