package tribore.onlinecinema.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tribore.onlinecinema.data.repository.CinemaRepositoryImpl
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.usecase.GetCinemaUseCase

import java.io.IOException

class HomeViewModel(private val cinemaRepositoryImpl: CinemaRepositoryImpl): ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _movie = MutableLiveData<List<CinemaDomainModel>>()
    val movie: LiveData<List<CinemaDomainModel>> = _movie

    init {
        getCinemaAll()
    }

    private fun getCinemaAll() {
        viewModelScope.launch {
            try {
                _movie.value = GetCinemaUseCase(cinemaRepositoryImpl).getCinema()
                _status.value = cinemaRepositoryImpl.getCinema()[0].posterPath

            } catch (networkError: IOException) {
                _status.value = "Fail"
            }
        }
    }
}