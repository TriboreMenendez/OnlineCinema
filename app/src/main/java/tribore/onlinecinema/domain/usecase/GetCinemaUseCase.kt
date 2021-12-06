package tribore.onlinecinema.domain.usecase

import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.repository.CinemaRepository

class GetCinemaUseCase(private val cinemaRepository: CinemaRepository) {

    suspend fun getCinema(): List<CinemaDomainModel> {
        return cinemaRepository.getCinema()
    }
}