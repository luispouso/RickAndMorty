package android.luispouso.rickandmorty.features.characters.main.repository.data.apis.remoteresult

import android.luispouso.rickandmorty.features.characters.main.repository.data.apis.remoteresult.info.Info
import android.luispouso.rickandmorty.features.characters.main.repository.data.model.CharactersResponse

data class CharactersRemoteResult(
    val info: Info,
    val results: List<CharactersResponse>
)