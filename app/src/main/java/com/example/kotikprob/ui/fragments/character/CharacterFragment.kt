package com.example.kotikprob.ui.fragments.character

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotikprob.R
import com.example.kotikprob.common.base.BaseFragment
import com.example.kotikprob.databinding.FragmentCharacterBinding
import com.example.kotikprob.ui.adapter.character.CharacterAdapter
import com.example.kotikprob.ui.adapter.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var binding: FragmentCharacterBinding
    private val characterAdapter =
        CharacterAdapter(this::setOnItemClickListener, this::setOnLongClickListener)
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerCharacter.layoutManager = LinearLayoutManager(context)
        recyclerCharacter.adapter = characterAdapter.withLoadStateFooter(LoadStateAdapter {
            characterAdapter.retry()
        })

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
//            swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            this.lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
        Toast.makeText(requireContext(), "Character", Toast.LENGTH_LONG).show()
    }

    private fun setOnItemClickListener(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailFragment(id)
        )
    }

    private fun setOnLongClickListener(image: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment(image)
        )
    }



    override fun swiperefresh() {
        binding.characterSwipeRefreshLayout.setOnRefreshListener {
            runnable = Runnable {
                binding.characterSwipeRefreshLayout.isRefreshing
            }
            handler.postDelayed(
                runnable, 3000.toLong()
            )
        }
    }
}
