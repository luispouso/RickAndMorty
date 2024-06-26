package android.luispouso.rickandmorty.domain.repository

import android.luispouso.rickandmorty.data.apis.model.CharacterDraft
import android.luispouso.rickandmorty.data.apis.model.toCharacterModel
import android.luispouso.rickandmorty.data.apis.remoteresult.CharactersRemoteResult
import android.luispouso.rickandmorty.data.datasources.RetrofitService
import android.luispouso.rickandmorty.domain.model.CharacterModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val retrofitService: RetrofitService) {

    private var characterModels: List<CharacterModel> = listOf()
    suspend fun loadCharacters(): List<CharacterModel> {
        return try {
            val result = getCharacterDraft()
            val characters: List<CharacterDraft> = result.results
            characters.map { it.toCharacterModel() }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getCharacterById(characterId: String): CharacterModel? {
        return characterModels.find { it.id == characterId }
    }

    private suspend fun getCharacterDraft(): CharactersRemoteResult {
        val response: Response<CharactersRemoteResult> = retrofitService.listCharacters()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Response body is null")
        } else {
            throw Exception("Response not successful: ${response.code()}")
        }
    }
}