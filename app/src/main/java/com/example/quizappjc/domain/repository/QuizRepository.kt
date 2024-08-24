package com.example.quizappjc.domain.repository

import com.example.quizappjc.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzed(amount :Int, category : Int , difficulty : String, type: String):List<Quiz>
}