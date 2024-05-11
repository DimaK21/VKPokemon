package ru.kryu.vkpokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoRepository
import ru.kryu.vkpokemon.feature.pokemoninfo.domain.PokemonInfoUseCase
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListRepository
import ru.kryu.vkpokemon.feature.pokemonlist.domain.PokemonListUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providePokemonListUseCase(pokemonListRepository: PokemonListRepository): PokemonListUseCase =
        PokemonListUseCase(pokemonListRepository)

    @Provides
    fun providePokemonInfoUseCase(pokemonInfoRepository: PokemonInfoRepository): PokemonInfoUseCase =
        PokemonInfoUseCase(pokemonInfoRepository)
}
