package android.luispouso.rickandmorty.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun navigateToMainActivity(navigationCallback: () -> Unit) {
        viewModelScope.launch {
            delay(1000)
            navigationCallback()
        }
    }
}