package android.luispouso.rickandmorty.features.characters.main.vm

import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.luispouso.rickandmorty.features.characters.main.repository.CharacterRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val characterRepository: CharacterRepository): ViewModel() {

    private val _characters = MutableLiveData<List<Characters>>()
    val characters: LiveData<List<Characters>> get() = _characters

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