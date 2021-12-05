package tribore.onlinecinema.data.network

import tribore.onlinecinema.data.network.models.ResultApiModel
import tribore.onlinecinema.domain.models.CinemaModel
import tribore.onlinecinema.domain.models.GenresModel

fun ResultApiModel.toDomain(): CinemaModel {

    val genresDomainModel: List<GenresModel> = genres.map {
        GenresModel(
            name = it.name
        )
    }

    return CinemaModel(
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