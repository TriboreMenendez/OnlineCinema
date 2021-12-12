package tribore.onlinecinema.data.repository

import tribore.onlinecinema.data.network.CinemaNetworkService
import tribore.onlinecinema.data.network.toDomain
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.repository.CinemaRepository

class CinemaRepositoryImpl(private val networkApi: CinemaNetworkService) : CinemaRepository {

    override suspend fun getCinema(): List<CinemaDomainModel> {
        return networkApi
            .getPlaylist()
            .results
            .map { result -> result.toDomain() }
    }
}