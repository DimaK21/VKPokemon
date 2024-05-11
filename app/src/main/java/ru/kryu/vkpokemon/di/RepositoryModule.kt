package ru.kryu.vkpokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kryu.vkpokemon.feature.pokemoninfo.data.PokemonInfoRepositoryImpl
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoRepository
import ru.kryu.vkpokemon.feature.pokemonlist.data.PokemonListRepositoryImpl
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListRepository
import ru.kryu.vkpokemon.network.NetworkClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonListRepository(networkClient: NetworkClient): PokemonListRepository =
        PokemonListRepositoryImpl(networkClient)

    @Provides
    @Singleton
    fun providePokemonInfoRepository(networkClient: NetworkClient): PokemonInfoRepository =
        PokemonInfoRepositoryImpl(networkClient)
}
