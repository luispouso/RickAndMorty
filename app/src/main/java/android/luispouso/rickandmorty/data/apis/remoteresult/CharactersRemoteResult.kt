package android.luispouso.rickandmorty.data.apis.remoteresult

import android.luispouso.rickandmorty.data.apis.model.CharacterDraft
import android.luispouso.rickandmorty.data.apis.remoteresult.info.Info

data class CharactersRemoteResult(
    val info: Info,
    val results: List<CharacterDraft>
)