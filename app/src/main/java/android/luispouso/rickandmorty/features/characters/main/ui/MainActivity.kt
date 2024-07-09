package android.luispouso.rickandmorty.features.characters.main.ui

import android.content.Context
import android.content.Intent
import android.luispouso.rickandmorty.core.ui.showToast
import android.luispouso.rickandmorty.databinding.ActivityMainBinding
import android.luispouso.rickandmorty.features.characters.detail.ui.CharacterDetailActivity
import android.luispouso.rickandmorty.features.characters.main.vm.MainViewModel
import android.luispouso.rickandmorty.features.characters.main.ui.adapter.CharacterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

   // private val retrofitService = RetrofitServiceFactory.makeRetrofitService()
    //private val repository = CharacterRepository(retrofitService)


   /* private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(repository)
    }*/

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchCharacters()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter(emptyList()) { character ->
            CharacterDetailActivity.navigate(this, character.id)
        }
        binding.rvCharacter.adapter = characterAdapter
    }

    private fun observeViewModel() {
        viewModel.characters.observe(this) { characters ->
            characterAdapter.updateCharacters(characters)
        }

        viewModel.error.observe(this) { errorMessage ->
            showToast(errorMessage)
        }
    }
}