package ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto

import ru.kryu.vkpokemon.network.Request

data class PokemonListRequest(val limit: Int, val offset: Int) : Request
