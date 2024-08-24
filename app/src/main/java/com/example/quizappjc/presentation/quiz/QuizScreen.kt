package com.example.quizappjc.presentation.quiz

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.quizappjc.presentation.common.QuizAppBar
import com.example.quizappjc.presentation.util.Dimens
import com.example.quizappjc.R
import com.example.quizappjc.presentation.common.ButtonBox
import com.example.quizappjc.presentation.nav_graph.Routes
import com.example.quizappjc.presentation.quiz.component.QuizInterface
import com.example.quizappjc.presentation.quiz.component.ShimmerEffectQuizInterface
import com.example.quizappjc.presentation.util.Constants
import kotlinx.coroutines.launch


//@Preview
//@Composable
//fun PrevQuizScreen() {
//    QuizScreen(
//        numOfQuiz = 12,
//        quizCategory = "Gk",
//        quizDifficulty = "Easy",
//        quizType = "Easy",
//        event = {},
//        state = StateQuizScreen())
//
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
    navController: NavController
) {

    BackHandler {
        goToHomeScreen(navController)
    }

    LaunchedEffect(key1 = Unit) {

        val difficulty = when (quizDifficulty) {
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }
        val type = when (quizType) {
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(
            EventQuizScreen.GetQuizzes(
                numOfQuiz,
                Constants.categoriesMap[quizCategory]!!,
                difficulty,
                type
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {
            goToHomeScreen(navController)
        }

        Column(
            modifier = Modifier
                .padding(Dimens.SmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.LargerSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Question : $numOfQuiz",
                    color = colorResource(id = R.color.blue_grey)
                )
                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.blue_grey)
                )
            }
            Spacer(modifier = Modifier.size(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.VerySmallViewHeight)
                    .clip(
                        RoundedCornerShape(Dimens.MediumCornerRadius)
                    )
                    .background(
                        colorResource(id = R.color.blue_grey)
                    )
            )
            Spacer(modifier = Modifier.height(Dimens.LargerSpacerHeight))

            // Quiz InterFace
            if (quizFetched(state)) {

                val pagerState = rememberPagerState() { state.quizState.size }

                HorizontalPager(state = pagerState) {index->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        onOptionSelected = {selectedIndex->
                            event(EventQuizScreen.SetOptionSelected(index,selectedIndex))
                        },
                        qNumber = index + 1,
                        quizState = state.quizState[index]
                    )
                }

                val buttonText by remember {

                    derivedStateOf {
                        when(pagerState.currentPage){
                            0->{
                                listOf("","Next")
                            }
                            state.quizState.size-1 -> {
                                listOf("Previous","Submit")
                            }
                            else ->{
                                listOf("Previous","Next")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()
                    if (buttonText[0].isNotEmpty()){
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize
                        ) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage -1) }
                        }
                    }else{
                        ButtonBox(
                            text = "",
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            containerColor = colorResource(id = R.color.mid_night_blue),
                            borderColor = colorResource(id = R.color.mid_night_blue)
                        ){

                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        fraction = 1f,
                        borderColor = colorResource(id = R.color.orange),
                        containerColor = if (pagerState.currentPage == state.quizState.size-1) colorResource(id = R.color.orange)else{ colorResource(id = R.color.dark_slate_blue)},
                        textColor = colorResource(id = R.color.white),
                        fontSize = Dimens.SmallTextSize
                    ) {
                        if (pagerState.currentPage== state.quizState.size -1){
                            // ToDO
                            navController.navigate(Routes.ScoreScreen.passNumOfQuestionAndCorrectAns(state.quizState.size,state.score))

                                Log.d("score",state.score.toString())

                        }else{
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage +1) }

                        }
                    }
                }
            }

        }
    }
}

fun goToHomeScreen(navController: NavController) {
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){
            inclusive = true
        }
    }
}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {


    return when {
        state.isLoading -> {
            ShimmerEffectQuizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else -> {
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}
