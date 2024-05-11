package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListUseCase
import ru.kryu.vkpokemon.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(pokemonListUseCase: PokemonListUseCase) :
    ViewModel() {

    private val _screenState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.Loading)
    val screenState: StateFlow<PokemonListState>
        get() = _screenState
    private val _toastLiveData = SingleLiveEvent<String>()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData

    fun refreshClicked() {

    }

    fun getNextPage() {

    }
}
