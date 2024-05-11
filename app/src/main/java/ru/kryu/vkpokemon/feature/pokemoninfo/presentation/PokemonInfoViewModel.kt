package ru.kryu.vkpokemon.feature.pokemoninfo.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoUseCase
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(pokemonInfoUseCase: PokemonInfoUseCase) :
    ViewModel() {
}