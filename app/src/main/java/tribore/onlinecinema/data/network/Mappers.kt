package tribore.onlinecinema.data.network

import tribore.onlinecinema.data.network.models.ResultApiModel
import tribore.onlinecinema.domain.models.CinemaDomainModel
import tribore.onlinecinema.domain.models.GenresDomainModel

fun ResultApiModel.toDomain(): CinemaDomainModel {

    val genresDomainModel: List<GenresDomainModel> = genres.map {
        GenresDomainModel(
            name = it.name
        )
    }

    return CinemaDomainModel(
        adult = this.adult,
        genres = genresDomainModel,
        id = this.id,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}