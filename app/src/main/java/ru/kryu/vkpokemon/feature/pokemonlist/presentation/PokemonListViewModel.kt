package ru.kryu.vkpokemon.feature.pokemonlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListUseCase
import ru.kryu.vkpokemon.util.Resource
import ru.kryu.vkpokemon.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonListUseCase: PokemonListUseCase) :
    ViewModel() {

    private val _screenState: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState.Loading)
    val screenState: StateFlow<PokemonListState>
        get() = _screenState
    private val _toastLiveData = SingleLiveEvent<String>()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData

    init {
        getPokemon(START_LIMIT, START_OFFSET)
    }

    fun refreshClicked() {
        _screenState.value = PokemonListState.Loading
        getPokemon(START_LIMIT, START_OFFSET)
    }

    fun getNextPage() {
        if (_screenState.value is PokemonListState.Content) {
            val limit = (_screenState.value as PokemonListState.Content).content.nextLimit
            val offset = (_screenState.value as PokemonListState.Content).content.nextOffset
            if (limit != null && offset != null) {
                getPokemon(limit, offset)
                (_screenState.value as PokemonListState.Content).isBottomLoading = true
            }
        }

    }

    private fun getPokemon(limit: Int, offset: Int) {
        viewModelScope.launch {
            pokemonListUseCase.getPokemonList(limit, offset).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        if (_screenState.value is PokemonListState.Content) {
                            (_screenState.value as PokemonListState.Content).isBottomLoading = false
                        } else {
                            _screenState.value = PokemonListState.Error
                        }
                        _toastLiveData.postValue(resource.message!!)
                    }

                    is Resource.Success -> {
                        if (_screenState.value is PokemonListState.Content) {
                            with(_screenState.value as PokemonListState.Content) {
                                isBottomLoading = false
                                content.nextLimit = resource.data?.nextLimit
                                content.nextOffset = resource.data?.nextOffset
                                content.pokemonList.addAll(resource.data?.pokemonList!!)
                            }

                        } else {
                            _screenState.value = PokemonListState.Content(resource.data!!)
                        }

                    }
                }
            }
        }
    }

    companion object {
        private const val START_LIMIT = 20
        private const val START_OFFSET = 0
    }
}
