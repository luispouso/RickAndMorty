package android.luispouso.rickandmorty.presentation.ui.list

import android.luispouso.rickandmorty.common.extensions.showToast
import android.luispouso.rickandmorty.data.datasources.RetrofitServiceFactory
import android.luispouso.rickandmorty.databinding.FragmentListCharacterBinding
import android.luispouso.rickandmorty.di.ViewModelFactory
import android.luispouso.rickandmorty.domain.repository.CharacterRepository
import android.luispouso.rickandmorty.presentation.ui.list.adapter.CharacterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentListCharacterBinding
    private val retrofitService = RetrofitServiceFactory.makeRetrofitService()
    private val repository = CharacterRepository(retrofitService)
    private lateinit var characterAdapter: CharacterAdapter

    private val viewModel: CharacterListViewModel by viewModels {
        ViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchCharacters()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter(emptyList()) { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToListDetailFragment(character.id))
        }
        binding.rvCharacter.adapter = characterAdapter
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            characterAdapter.updateCharacters(characters)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            showToast(errorMessage)
        }
    }
}