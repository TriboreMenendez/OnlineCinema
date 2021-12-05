package tribore.onlinecinema.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresApiModel(
    @Json(name= "name") val name: String
)