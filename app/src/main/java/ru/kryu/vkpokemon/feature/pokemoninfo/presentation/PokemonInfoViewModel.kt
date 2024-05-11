package ru.kryu.vkpokemon.feature.pokemoninfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoUseCase
import ru.kryu.vkpokemon.util.Resource
import ru.kryu.vkpokemon.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val pokemonInfoUseCase: PokemonInfoUseCase,
    state: SavedStateHandle
) : ViewModel() {

    private val _screenState: MutableStateFlow<PokemonInfoState> =
        MutableStateFlow(PokemonInfoState.Loading)
    val screenState: StateFlow<PokemonInfoState>
        get() = _screenState
    private val _toastLiveData = SingleLiveEvent<String>()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData
    private val id: Int

    init {
        id = state.get<Int>(POKEMON_ID)!!
        getInfo(id)
    }

    fun refreshClicked() {
        _screenState.value = PokemonInfoState.Loading
        getInfo(id)
    }

    private fun getInfo(id: Int) {
        viewModelScope.launch {
            pokemonInfoUseCase.getPokemonInfo(id).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _screenState.value = PokemonInfoState.Error
                        _toastLiveData.postValue(resource.message!!)
                    }

                    is Resource.Success -> {
                        _screenState.value = PokemonInfoState.Content(resource.data!!)
                    }
                }
            }
        }
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"
    }
}
