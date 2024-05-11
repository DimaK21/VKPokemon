package ru.kryu.vkpokemon.feature.pokemoninfo.data.mapper

import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.PokemonInfoResponse
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.model.Pokemon

object PokemonInfoResponsePokemonMapper {
    fun map(pokemonInfoResponse: PokemonInfoResponse): Pokemon {
        val icon: String = pokemonInfoResponse.sprites.other.officialArtwork.frontDefault
            ?: pokemonInfoResponse.sprites.frontDefault!!
        return Pokemon(
            id = pokemonInfoResponse.id,
            name = pokemonInfoResponse.name,
            icon = icon,
            forms = pokemonInfoResponse.forms.map { it.name },
            height = pokemonInfoResponse.height / 10f,
            weight = pokemonInfoResponse.weight / 10f,
            abilities = pokemonInfoResponse.abilities.map { it.ability.name }
        )
    }
}