package android.luispouso.rickandmorty.features.characters.main.repository.data.model

import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.luispouso.rickandmorty.features.characters.main.repository.data.apis.model.attributes.Origin
import android.luispouso.rickandmorty.features.characters.main.repository.data.apis.model.attributes.Location


data class CharactersResponse(
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

fun CharactersResponse.toCharacters(): Characters {
    return Characters(
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