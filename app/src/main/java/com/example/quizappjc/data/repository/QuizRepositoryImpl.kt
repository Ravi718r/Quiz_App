package com.example.quizappjc.data.repository

import com.example.quizappjc.data.remote.QuizApi
import com.example.quizappjc.domain.model.Quiz
import com.example.quizappjc.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi : QuizApi
):QuizRepository {
    override suspend fun getQuizzed(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount,category,difficulty,type).results
    }
}