package tribore.onlinecinema.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import tribore.onlinecinema.data.repository.CinemaRepositoryImpl
import tribore.onlinecinema.ui.view_model.HomeViewModel

val appModule = module {

    single<HomeViewModel> {
        HomeViewModel(get<CinemaRepositoryImpl>())
    }
}