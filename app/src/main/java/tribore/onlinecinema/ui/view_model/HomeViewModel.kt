package tribore.onlinecinema.ui.view_model

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tribore.onlinecinema.data.repository.CinemaRepositoryImpl
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.usecase.GetCinemaUseCase

import java.io.IOException

class HomeViewModel(private val cinemaRepositoryImpl: CinemaRepositoryImpl) : ViewModel() {

    private val _playlist = MutableLiveData<List<CinemaDomainModel>>()
    val playlist: LiveData<List<CinemaDomainModel>> = _playlist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = GetCinemaUseCase(cinemaRepositoryImpl).getCinema()
            _playlist.postValue(playlist)

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            _eventNetworkError.value = false
            _playlist.value = listOf()
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}