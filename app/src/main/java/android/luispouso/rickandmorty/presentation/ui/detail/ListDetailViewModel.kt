package android.luispouso.rickandmorty.presentation.ui.detail

import android.luispouso.rickandmorty.domain.model.CharacterModel
import android.luispouso.rickandmorty.domain.repository.CharacterRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListDetailViewModel constructor(private val characterRepository: CharacterRepository): ViewModel(){

    private val _character = MutableLiveData<CharacterModel>()
    val character: LiveData<CharacterModel> get() = _character


    fun getCharacter(characterId: String) {
        viewModelScope.launch {
            try {
                val character = characterRepository.getCharacterById(characterId)
                if (character != null) {
                    _character.postValue(character)
                } else {
                    val characters = characterRepository.loadCharacters()
                    _character.postValue(characterRepository.getCharacterById(characterId))
                }
            } catch (e: Exception) {
               // TODO
            }
        }
    }
}