package com.example.kotikprob.servicelocator

import com.example.kotikprob.data.network.RetrofitClient
import com.example.kotikprob.data.repository.CharacterRepository
import com.example.kotikprob.data.repository.EpisodeRepository
import com.example.kotikprob.data.repository.LocationRepository
import com.example.kotikprob.presentation.ui.fragments.character.CharacterViewModel
import com.example.kotikprob.presentation.ui.fragments.character.detail.CharacterDetailViewModel
import com.example.kotikprob.presentation.ui.fragments.episode.EpisodeViewModel
import com.example.kotikprob.presentation.ui.fragments.episode.detail.EpisodeDetailViewModel
import com.example.kotikprob.presentation.ui.fragments.location.LocationViewModel
import com.example.kotikprob.presentation.ui.fragments.location.detail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodeApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}
val repositoriesModel = module {
    factory { CharacterRepository(get()) }
    factory { EpisodeRepository(get()) }
    factory { LocationRepository(get()) }
}

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
}