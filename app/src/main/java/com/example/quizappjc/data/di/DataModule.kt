package com.example.quizappjc.data.di

import com.example.quizappjc.data.remote.QuizApi
import com.example.quizappjc.data.repository.QuizRepositoryImpl
import com.example.quizappjc.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {



    @Provides
    @Singleton
    fun provideQuizApi(): QuizApi {
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }
    @Provides
    @Singleton
    fun provideQuizRepository(quizApi : QuizApi) : QuizRepository {
        return QuizRepositoryImpl(quizApi )
    }
}