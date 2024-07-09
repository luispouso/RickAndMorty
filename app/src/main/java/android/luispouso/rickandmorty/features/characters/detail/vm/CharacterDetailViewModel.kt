package android.luispouso.rickandmorty.features.characters.detail.vm

import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.luispouso.rickandmorty.features.characters.main.repository.CharacterRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository): ViewModel(){

    private val _character = MutableLiveData<Characters?>()
    val character: MutableLiveData<Characters?> get() = _character

    fun getCharacter(characterId: String) {
        viewModelScope.launch {
                val character = characterRepository.getCharacterById(characterId)
                if (character != null) {
                    _character.postValue(character)
                    Log.i("LPM", "Character not null")
                } else {
                    _character.postValue(null)
                    Log.i("LPM", "Character null")
                }
            }
        }
    }