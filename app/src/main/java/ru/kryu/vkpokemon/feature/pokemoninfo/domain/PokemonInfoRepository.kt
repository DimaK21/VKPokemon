package ru.kryu.vkpokemon.feature.pokemoninfo.domain

import kotlinx.coroutines.flow.Flow
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.model.Pokemon
import ru.kryu.vkpokemon.util.Resource

interface PokemonInfoRepository {
    fun getPokemonInfo(id: Int): Flow<Resource<Pokemon>>
}