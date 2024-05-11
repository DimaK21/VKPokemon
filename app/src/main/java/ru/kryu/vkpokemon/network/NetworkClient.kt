package ru.kryu.vkpokemon.network

interface NetworkClient {

    suspend fun doRequest(request: Request): Response
}
