package com.example.kotikprob.presentation.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.databinding.FragmentCharacterDetailBinding
import com.example.kotikprob.presentation.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel,
            FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    private val viewModel: CharacterDetailViewModel by viewModel()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
        setupRequests()
    }


    private fun setupRequests() = with(binding) {
        viewModel.characterState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            groupMain.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    it.data?.let { data ->
                        binding.textName.text = data.name
                        binding.tvId.text = data.id.toString()
                        binding.textGender.text = data.gender
                        binding.textSpecies.text = data.species
                        binding.textLocation.text = data.location.toString()
                        binding.imageCharacterDetail.load(data.image)
                    }
                }
            }
        }
    }
}



