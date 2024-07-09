package android.luispouso.rickandmorty.di

import android.luispouso.rickandmorty.features.characters.detail.vm.CharacterDetailViewModel
import android.luispouso.rickandmorty.features.characters.main.repository.CharacterRepository
import android.luispouso.rickandmorty.features.characters.main.vm.MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val characterRepository: CharacterRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(characterRepository) as T
        } else if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            return CharacterDetailViewModel(characterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}