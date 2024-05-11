package ru.kryu.vkpokemon.feature.pokemonlist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kryu.vkpokemon.feature.pokemonlist.data.mapper.PokemonListResponsePokemonPackMapper
import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.PokemonListRequest
import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.PokemonListResponse
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListRepository
import ru.kryu.vkpokemon.feature.pokemonlist.domain.model.PokemonPack
import ru.kryu.vkpokemon.network.NetworkClient
import ru.kryu.vkpokemon.network.RetrofitNetworkClient
import ru.kryu.vkpokemon.util.Resource

class PokemonListRepositoryImpl constructor(
    private val networkClient: NetworkClient
) : PokemonListRepository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<Resource<PokemonPack>> = flow {
        val response = networkClient.doRequest(PokemonListRequest(limit = limit, offset = offset))
        emit(
            when (response.resultCode) {
                RetrofitNetworkClient.CODE_NO_INTERNET, RetrofitNetworkClient.CODE_WRONG_REQUEST,
                RetrofitNetworkClient.CODE_EXCEPTION -> {
                    Resource.Error(
                        message = response.text ?: ERROR
                    )
                }

                RetrofitNetworkClient.CODE_SUCCESS -> {
                    Resource.Success(
                        PokemonListResponsePokemonPackMapper.map(
                            (response as PokemonListResponse)
                        )
                    )
                }

                else -> {
                    Resource.Error(
                        message = ("${response.resultCode} ${response.text}")
                    )
                }
            }
        )
    }

    companion object {
        private const val ERROR = "Error"
    }
}
