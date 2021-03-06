package tribore.onlinecinema.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultApiModel(
    @Json(name = "adult")val adult: Boolean,
    @Json(name = "genres")val genres: List<GenresApiModel>,
    @Json(name = "id")val id: Int,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview")val overview: String,
    @Json(name = "popularity")val popularity: Double,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title")val title: String,
    @Json(name = "video")val video: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)