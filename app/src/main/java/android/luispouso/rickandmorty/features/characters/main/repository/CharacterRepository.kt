package android.luispouso.rickandmorty.features.characters.main.repository

import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.luispouso.rickandmorty.features.characters.main.repository.data.apis.remoteresult.CharactersRemoteResult
import android.luispouso.rickandmorty.features.characters.main.repository.data.datasources.RetrofitService
import android.luispouso.rickandmorty.features.characters.main.repository.data.model.CharactersResponse
import android.luispouso.rickandmorty.features.characters.main.repository.data.model.toCharacters
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val retrofitService: RetrofitService) {

    private val charactersCache: MutableList<Characters> = mutableListOf()
    suspend fun loadCharacters(): List<Characters> {
        return try {
            val charactersResponse: List<CharactersResponse> = getCharacterResponse().results
            val characters = charactersResponse.map { it.toCharacters() }
            charactersCache.apply {
                addAll(characters)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getCharacterById(characterId: String): Characters? {
        return charactersCache.find { it.id == characterId }
    }

    private suspend fun getCharacterResponse(): CharactersRemoteResult {
        val response: Response<CharactersRemoteResult> = retrofitService.listCharacters()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Response body is null")
        } else {
            throw Exception("Response not successful: ${response.code()}")
        }
    }
}