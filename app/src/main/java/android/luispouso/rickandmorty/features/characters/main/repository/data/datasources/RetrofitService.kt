package android.luispouso.rickandmorty.features.characters.main.repository.data.datasources

import android.luispouso.rickandmorty.features.characters.main.repository.data.apis.remoteresult.CharactersRemoteResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

interface RetrofitService {

    @GET("character")
    suspend fun listCharacters(): Response<CharactersRemoteResult>
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceFactory {

    @Singleton
    @Provides
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory((GsonConverterFactory.create()))
            .build().create(RetrofitService::class.java)
    }
}