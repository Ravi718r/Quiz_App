package com.example.quizappjc.domain.di

import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappjc.data.remote.QuizApi
import com.example.quizappjc.data.repository.QuizRepositoryImpl
import com.example.quizappjc.domain.repository.QuizRepository
import com.example.quizappjc.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
fun provideGetQuizzesUseCases(quizRepository: QuizRepository):GetQuizzesUseCases{
    return GetQuizzesUseCases(quizRepository)
}
}