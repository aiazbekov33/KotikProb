package com.example.kotikprob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotikprob.databinding.ActivityMainBinding
import com.example.kotikprob.ui.adapter.character.CharacterAdapter
import com.example.kotikprob.ui.adapter.paging.LoadStateAdapter
import com.example.kotikprob.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val characterAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        setupRequests()
    }

    private fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerCharacter.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerCharacter.adapter = characterAdapter.withLoadStateFooter(LoadStateAdapter {
            characterAdapter.retry()
        })

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
        }
    }

    private fun setupRequests() {
        viewModel.fetchCharacters().observe(this, {
            this.lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
    }
}




