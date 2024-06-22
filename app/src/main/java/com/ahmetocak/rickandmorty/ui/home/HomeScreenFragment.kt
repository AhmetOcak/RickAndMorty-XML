package com.ahmetocak.rickandmorty.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ahmetocak.rickandmorty.databinding.FragmentHomeScreenBinding
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import com.ahmetocak.rickandmorty.ui.home.adapter.character.CharacterItemClickListener
import com.ahmetocak.rickandmorty.ui.home.adapter.character.CharactersAdapter
import com.ahmetocak.rickandmorty.ui.home.adapter.location.LocationItemClickListener
import com.ahmetocak.rickandmorty.ui.home.adapter.location.LocationsAdapter
import com.ahmetocak.rickandmorty.ui.home.decoration.CharacterItemDecoration
import com.ahmetocak.rickandmorty.ui.home.decoration.LocationItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationsAdapter = LocationsAdapter(
            onSetLocationImages = { locationName ->
                AppCompatResources.getDrawable(
                    requireContext(),
                    viewModel.setLocationImage(locationName)
                )!!
            },
            object : LocationItemClickListener {
                override fun onLocationItemClick(locations: Locations) {
                    viewModel.getCharacters(locations.residents)
                }
            },
            initializeCharacterList = { residents ->
                viewModel.getCharacters(residents)
            }
        )
        binding.rvLocations.apply {
            adapter = locationsAdapter
            addItemDecoration(LocationItemDecoration())
        }

        val charactersAdapter = CharactersAdapter(
            setCharacterGender = { gender ->
                AppCompatResources.getDrawable(
                    requireContext(),
                    viewModel.setCharacterGenderImage(gender)
                )!!
            },
            characterItemClickListener = object : CharacterItemClickListener {
                override fun onCharacterItemClick(item: Character) {
                    findNavController().navigate(
                        HomeScreenFragmentDirections
                            .actionHomeScreenFragmentToCharacterScreenFragment(item)
                    )
                }
            }
        )
        binding.rvCharacters.apply {
            adapter = charactersAdapter
            addItemDecoration(CharacterItemDecoration())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.value.locationList.collect { pagingData ->
                    locationsAdapter.submitData(pagingData)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.isLoading = uiState.isLoading
                    charactersAdapter.submitList(uiState.characterList)

                    if (uiState.errorMessages.isNotEmpty()) {
                        val message = uiState.errorMessages.first()
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.consumedErrorMessage(message)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}