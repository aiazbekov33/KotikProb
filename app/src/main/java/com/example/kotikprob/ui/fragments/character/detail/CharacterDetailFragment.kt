package com.example.kotikprob.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.common.resource.Resource
import com.example.kotikprob.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailFragmentArgs by navArgs()

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
        setupRequests()
    }


    private fun setupRequests() = with(binding) {
        viewModel.fetchCharacter(args.id).observe(viewLifecycleOwner, {
            progressBar.isVisible = it is Resource.Loading
            groupMain.isVisible = it !is Resource.Loading
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
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
        })
    }
}



