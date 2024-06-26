package android.luispouso.rickandmorty.presentation.ui.list

import android.luispouso.rickandmorty.domain.model.CharacterModel
import android.luispouso.rickandmorty.domain.repository.CharacterRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterListViewModel constructor(private val characterRepository: CharacterRepository): ViewModel() {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characters = characterRepository.loadCharacters()
                _characters.postValue(characters)
            } catch (e: Exception) {
                _error.postValue("Failed to load characters: ${e.message}")
            }
        }
    }
}