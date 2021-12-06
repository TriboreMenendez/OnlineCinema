package tribore.onlinecinema.domain.repository

import tribore.onlinecinema.domain.models.CinemaDomainModel

interface CinemaRepository {
    suspend fun getCinema(): List<CinemaDomainModel>
}