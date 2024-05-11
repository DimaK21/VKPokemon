package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListUseCase

class PokemonListViewModel constructor(pokemonListUseCase: PokemonListUseCase) : ViewModel() {

    private val _screenState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.Loading)
    val screenState: StateFlow<PokemonListState>
        get() = _screenState
}
