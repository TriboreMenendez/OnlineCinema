package tribore.onlinecinema.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CinemaApiModel(
    @Json(name = "results") val results: List<ResultApiModel>
)