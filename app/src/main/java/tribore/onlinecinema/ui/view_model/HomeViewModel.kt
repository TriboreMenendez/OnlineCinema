package tribore.onlinecinema.ui.view_model

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import tribore.onlinecinema.CinemaApplication
import tribore.onlinecinema.R
import tribore.onlinecinema.data.repository.CinemaRepositoryImpl
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.models.GenresDomainModel
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

    // Выбранный фильм
    private val _selectedCinema = MutableLiveData<CinemaDomainModel>()
    val selectedCinema: LiveData<CinemaDomainModel> = _selectedCinema

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
        } catch (e: IOException) {
            _isNetworkError.value = true
            _isStatusLoad.value = false
            _playlist.value = listOf()
        }
    }

    fun test(cinema: CinemaDomainModel) {
        _selectedCinema.value = cinema
    }
}