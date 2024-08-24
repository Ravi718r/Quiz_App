package com.example.quizappjc.data.remote.dto

import com.example.quizappjc.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)