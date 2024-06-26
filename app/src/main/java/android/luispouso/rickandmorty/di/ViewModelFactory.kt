package android.luispouso.rickandmorty.di

import android.luispouso.rickandmorty.domain.repository.CharacterRepository
import android.luispouso.rickandmorty.presentation.ui.detail.ListDetailViewModel
import android.luispouso.rickandmorty.presentation.ui.list.CharacterListViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val characterRepository: CharacterRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            return CharacterListViewModel(characterRepository) as T
        } else if (modelClass.isAssignableFrom(ListDetailViewModel::class.java)) {
            return ListDetailViewModel(characterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}