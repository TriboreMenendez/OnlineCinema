package tribore.onlinecinema.domain.models

import java.text.SimpleDateFormat
import java.util.*

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
    val voteAverage: Double, // Рейтинг от 1 до 10
    val voteCount: Int
) {
    val converterDate: String
    get() {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
        val formatedDate = formatter.parse(releaseDate)
        return SimpleDateFormat("yyyy", Locale.ROOT).format(formatedDate ?: "")
    }
}

