package com.example.quizappjc.presentation.quiz

import androidx.lifecycle.Lifecycle

sealed class EventQuizScreen {

    data class GetQuizzes(val numOfQuizzes : Int, val category : Int,val difficulty : String,val type : String):EventQuizScreen()

    data class SetOptionSelected (val quizStateIndex : Int,val selectedOption: Int):EventQuizScreen()
}