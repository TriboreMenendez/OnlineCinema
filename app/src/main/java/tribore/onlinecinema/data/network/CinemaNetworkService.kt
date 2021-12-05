package tribore.onlinecinema.data.network

import retrofit2.Call
import retrofit2.http.GET
import tribore.onlinecinema.data.network.models.CinemaApiModel

interface CinemaNetworkService {
    @GET("LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/")
    suspend fun getPlaylist(): CinemaApiModel
}