package ru.kryu.vkpokemon.feature.pokemonlist.data.mapper

import android.net.Uri
import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.PokemonListResponse
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonPack

object PokemonListResponsePokemonPackMapper {
    fun map(pokemonListResponse: PokemonListResponse): PokemonPack {
        val uri = Uri.parse(pokemonListResponse.next)
        val limit = uri.getQueryParameter("limit")
        val offset = uri.getQueryParameter("offset")
        return PokemonPack(
            count = pokemonListResponse.count,
            nextLimit = limit?.toIntOrNull(),
            nextOffset = offset?.toIntOrNull(),
            pokemonList = pokemonListResponse.results.map {
                ResultDtoPokemonItemMapper.map(it)
            }.toMutableList(),
        )
    }
}