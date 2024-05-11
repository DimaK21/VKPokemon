package ru.kryu.vkpokemon.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.PokemonInfoResponse
import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.PokemonListResponse

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Response<PokemonInfoResponse>
}
