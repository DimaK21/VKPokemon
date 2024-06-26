package ru.kryu.vkpokemon.feature.pokemonlist.domain

import kotlinx.coroutines.flow.Flow
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonPack
import ru.kryu.vkpokemon.util.Resource
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(private val pokemonListRepository: PokemonListRepository) {
    fun getPokemonList(limit: Int, offset: Int): Flow<Resource<PokemonPack>> {
        return pokemonListRepository.getPokemonList(limit, offset)
    }
}