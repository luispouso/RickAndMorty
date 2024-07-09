package android.luispouso.rickandmorty.features.characters.detail.ui

import android.content.Context
import android.content.Intent
import android.luispouso.rickandmorty.R
import android.luispouso.rickandmorty.core.ui.loadImageWithGlide
import android.luispouso.rickandmorty.databinding.ActivityCharacterDetailBinding
import android.luispouso.rickandmorty.features.characters.detail.vm.CharacterDetailViewModel
import android.luispouso.rickandmorty.features.characters.main.domain.model.Characters
import android.luispouso.rickandmorty.features.characters.main.ui.MainActivity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    companion object {
        fun navigate(context: Context, characterId: String) {
            val intent = Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra("characterId", characterId)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterId = intent.getStringExtra("characterId") ?: return
        getValues(characterId)
    }

    private fun getValues(characterId: String) {
        viewModel.getCharacter(characterId)
        viewModel.character.observe(this) { character ->
            character?.let {
                with(binding) {
                    tvName.text = character.name
                    ivCharacter.loadImageWithGlide(Uri.parse(character.image))
                    tvStatus.text = character.status
                    tvSpecies.text = character.species
                    tvType.text = character.type
                    tvGender.text = character.gender
                    tvOrigin.text = character.origin
                    tvLocation.text = character.location
                    tvEpisode.text = character.episode
                    tvUrl.text = character.url
                    tvCreated.text = character.created
                }
            }
        }
    }
}