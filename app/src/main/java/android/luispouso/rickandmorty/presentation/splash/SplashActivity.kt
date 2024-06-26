package android.luispouso.rickandmorty.presentation.splash

import android.annotation.SuppressLint
import android.luispouso.rickandmorty.presentation.main.MainActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
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