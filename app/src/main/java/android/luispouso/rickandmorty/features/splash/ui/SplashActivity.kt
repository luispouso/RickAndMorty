package android.luispouso.rickandmorty.features.splash.ui

import android.annotation.SuppressLint
import android.luispouso.rickandmorty.features.characters.main.ui.MainActivity
import android.luispouso.rickandmorty.features.splash.vm.SplashViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        splashViewModel.navigateToMainActivity {
            MainActivity.navigate(this)
            finish()
        }
    }
}