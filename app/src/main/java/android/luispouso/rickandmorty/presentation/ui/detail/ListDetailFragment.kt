package android.luispouso.rickandmorty.presentation.ui.detail

import android.luispouso.rickandmorty.common.extensions.loadImageWithGlide
import android.luispouso.rickandmorty.data.datasources.RetrofitServiceFactory
import android.luispouso.rickandmorty.databinding.FragmentListDetailBinding
import android.luispouso.rickandmorty.di.ViewModelFactory
import android.luispouso.rickandmorty.domain.repository.CharacterRepository
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

class ListDetailFragment : Fragment() {

    private lateinit var binding: FragmentListDetailBinding
    private val args: ListDetailFragmentArgs by navArgs()
    private val retrofitService = RetrofitServiceFactory.makeRetrofitService()
    private val repository = CharacterRepository(retrofitService)
    private val viewModel: ListDetailViewModel by viewModels {
        ViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = args.id
        getValues(characterId)
    }

    private fun getValues(characterId: String) {
        viewModel.getCharacter(characterId)
        viewModel.character.observe(viewLifecycleOwner) { character ->
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