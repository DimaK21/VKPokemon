package ru.kryu.vkpokemon.feature.pokemoninfo.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kryu.vkpokemon.feature.pokemoninfo.data.mapper.PokemonInfoResponsePokemonMapper
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.PokemonInfoRequest
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.PokemonInfoResponse
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoRepository
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.model.Pokemon
import ru.kryu.vkpokemon.network.NetworkClient
import ru.kryu.vkpokemon.network.RetrofitNetworkClient
import ru.kryu.vkpokemon.util.Resource
import javax.inject.Inject

class PokemonInfoRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : PokemonInfoRepository {
    override fun getPokemonInfo(id: Int): Flow<Resource<Pokemon>> = flow {
        val response = networkClient.doRequest(PokemonInfoRequest(id))
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
                        PokemonInfoResponsePokemonMapper.map(
                            (response as PokemonInfoResponse)
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
