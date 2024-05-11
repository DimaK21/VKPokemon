package ru.kryu.vkpokemon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.vkpokemon.network.NetworkClient
import ru.kryu.vkpokemon.network.PokemonApiService
import ru.kryu.vkpokemon.network.RetrofitNetworkClient
import javax.inject.Singleton

const val BASE_URL = "https://pokeapi.co/api/v2/"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideNetworkClient(
        pokemonApiService: PokemonApiService,
        @ApplicationContext context: Context,
    ): NetworkClient = RetrofitNetworkClient(
        pokemonApiService = pokemonApiService,
        context = context
    )

    @Provides
    @Singleton
    fun providePokemonApiService(): PokemonApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
}
