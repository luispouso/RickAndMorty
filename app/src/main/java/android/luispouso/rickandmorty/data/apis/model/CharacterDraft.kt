package android.luispouso.rickandmorty.data.apis.model

import android.luispouso.rickandmorty.data.apis.model.attributes.Location
import android.luispouso.rickandmorty.data.apis.model.attributes.Origin
import android.luispouso.rickandmorty.domain.model.CharacterModel

data class CharacterDraft(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterDraft.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = this.id.toString(),
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.name,
        location = this.location.name,
        image = this.image,
        episode = this.episode.joinToString(separator = ","),
        url = this.url,
        created = this.created
    )
}