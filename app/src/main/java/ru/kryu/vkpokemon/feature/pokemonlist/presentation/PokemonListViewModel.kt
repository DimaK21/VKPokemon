package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListUseCase
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(pokemonListUseCase: PokemonListUseCase) : ViewModel() {

    private val _screenState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.Loading)
    val screenState: StateFlow<PokemonListState>
        get() = _screenState
}
