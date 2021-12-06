package tribore.onlinecinema.domain.models

data class CinemaDomainModel(
    val adult: Boolean,
    val genres: List<GenresDomainModel>,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: String,
    val voteAverage: Double,
    val voteCount: Int
)

