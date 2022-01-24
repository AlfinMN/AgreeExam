package com.test.agreeexam.config

import com.test.agreeexam.home.data.GamesAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModul {
    @Provides
    fun provideGamesAPI(): GamesAPI {
        return Connection.urlCon().create(GamesAPI::class.java)
    }
}