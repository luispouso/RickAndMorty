package android.luispouso.rickandmorty.data.datasources

import android.luispouso.rickandmorty.data.apis.remoteresult.CharactersRemoteResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("character")
    suspend fun listCharacters(): Response<CharactersRemoteResult>
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory((GsonConverterFactory.create()))
            .build().create(RetrofitService::class.java)
    }
}