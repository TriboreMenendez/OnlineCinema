package tribore.onlinecinema.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CinemaNetwork {

    val retrofitService: CinemaNetworkService by lazy {
        retrofit.create(CinemaNetworkService::class.java)
    }
}