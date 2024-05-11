package ru.kryu.vkpokemon.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kryu.vkpokemon.R
import ru.kryu.vkpokemon.feature.pokemoninfo.data.network.dto.PokemonInfoRequest
import ru.kryu.vkpokemon.feature.pokemonlist.data.network.dto.PokemonListRequest
import ru.kryu.vkpokemon.util.ConnectionChecker
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val pokemonApiService: PokemonApiService,
    private val context: Context,
) : NetworkClient {

    override suspend fun doRequest(request: Request): Response {
        if (!ConnectionChecker.isConnected(context)) {
            return Response().apply {
                resultCode = CODE_NO_INTERNET
                text = context.resources.getString(R.string.check_connection)
            }
        }
        return withContext(Dispatchers.IO) {
            when (request) {
                is PokemonListRequest -> getPokemonList(request.limit, request.offset)
                is PokemonInfoRequest -> getPokemonInfo(request.id)
                else -> Response().apply {
                    resultCode = CODE_WRONG_REQUEST
                    text = context.resources.getString(R.string.check_connection)
                }
            }
        }
    }

    private fun getPokemonList(limit: Int, offset: Int): Response {
        return try {
            val response = pokemonApiService.getPokemonList(limit, offset)
            if (response.code() == CODE_SUCCESS) {
                response.body()!!.apply {
                    resultCode = response.code()
                }
            } else {
                Response().apply {
                    resultCode = response.code()
                    text = response.message()
                }
            }
        } catch (e: Exception) {
            Response().apply {
                resultCode = CODE_EXCEPTION
                text = e.message
            }
        }
    }

    private fun getPokemonInfo(id: Int): Response {
        return try {
            val response = pokemonApiService.getPokemonInfo(id)
            if (response.code() == CODE_SUCCESS) {
                response.body()!!.apply {
                    resultCode = response.code()
                }
            } else {
                Response().apply {
                    resultCode = response.code()
                    text = response.message()
                }
            }
        } catch (e: Exception) {
            Response().apply {
                resultCode = CODE_EXCEPTION
                text = e.message
            }
        }
    }

    companion object {
        const val CODE_NO_INTERNET = -1
        const val CODE_EXCEPTION = -2
        const val CODE_SUCCESS = 200
        const val CODE_WRONG_REQUEST = 400
    }
}
