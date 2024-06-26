package android.luispouso.rickandmorty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: String = "",
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val image: String = "",
    val episode: String = "",
    val url: String = "",
    val created: String = ""
) : Parcelable


