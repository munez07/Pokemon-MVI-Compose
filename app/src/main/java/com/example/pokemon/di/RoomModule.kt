package com.example.pokemon.di

import android.content.Context
import androidx.room.Room
import com.example.pokemon.data.local.PokemonDao
import com.example.pokemon.data.local.PokemonDatabase
import com.example.pokemon.data.local.RemoteKeyDao
import com.example.pokemon.data.local.RoomConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomConverter(moshi: Moshi): RoomConverter = RoomConverter(moshi)

    @Provides
    @Singleton
    fun providePokemonDatabase(
        @ApplicationContext appContext: Context,
        roomConverter: RoomConverter
    ): PokemonDatabase {
        return Room.databaseBuilder(
            appContext,
            PokemonDatabase::class.java,
            "pokemon"
        ).addTypeConverter(roomConverter).build()
    }

    @Provides
    fun providePokemonDao(appDatabase: PokemonDatabase): PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Provides
    fun provideRemoteKeyDao(appDatabase: PokemonDatabase): RemoteKeyDao {
        return appDatabase.remoteKeyDao()
    }
}