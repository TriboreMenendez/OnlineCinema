package tribore.onlinecinema.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tribore.onlinecinema.data.network.CinemaNetworkService
import tribore.onlinecinema.data.repository.CinemaRepositoryImpl


const val BASE_URL = "https://gist.githubusercontent.com/"

val dataModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .build()
    }

    single<CinemaNetworkService> {
        get<Retrofit>().create(CinemaNetworkService::class.java)
    }

    single<CinemaRepositoryImpl> {
        CinemaRepositoryImpl(get<CinemaNetworkService>())
    }
}
