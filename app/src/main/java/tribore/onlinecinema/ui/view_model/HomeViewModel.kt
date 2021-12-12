package tribore.onlinecinema.ui.view_model

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tribore.onlinecinema.CinemaApplication
import tribore.onlinecinema.data.repository.CinemaRepositoryImpl
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.usecase.GetCinemaUseCase

import java.io.IOException

class HomeViewModel(
    private val cinemaRepositoryImpl: CinemaRepositoryImpl
) : AndroidViewModel(CinemaApplication()) {

    // Модель данных
    private val _playlist = MutableLiveData<List<CinemaDomainModel>>()
    val playlist: LiveData<List<CinemaDomainModel>> = _playlist

    // Контроль интернет соединения
    private var _isNetworkError = MutableLiveData<Boolean>(false)
    val isNetworkError: LiveData<Boolean> = _isNetworkError

    // Отображение статус бара
    private var _isStatusLoad = MutableLiveData<Boolean>(true)
    val isStatusLoad: LiveData<Boolean> = _isStatusLoad

    init {
        refreshDataFromNetwork()
    }

    // Апи запрос с обработкой исключений
    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = GetCinemaUseCase(cinemaRepositoryImpl).getCinema()
            _playlist.postValue(playlist)
            _isNetworkError.value = false
            _isStatusLoad.value = false

        } catch (networkError: IOException) {
            _isNetworkError.value = true
            _isStatusLoad.value = false
            _playlist.value = listOf()
        }
    }
}