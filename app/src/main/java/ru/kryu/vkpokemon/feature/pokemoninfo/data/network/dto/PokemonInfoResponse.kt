package ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Ability
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Cries
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Form
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.GameIndice
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.HeldItem
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Move
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Species
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Sprites
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Stat
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.pokemoninfoinner.Type
import ru.kryu.vkpokemon.network.Response

data class PokemonInfoResponse(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val cries: Cries,
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<HeldItem>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) : Response()
