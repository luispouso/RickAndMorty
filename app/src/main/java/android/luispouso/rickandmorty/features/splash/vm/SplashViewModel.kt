package android.luispouso.rickandmorty.features.splash.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    fun navigateToMainActivity(navigationCallback: () -> Unit) {
        viewModelScope.launch {
            delay(1000)
            navigationCallback()
        }
    }
}