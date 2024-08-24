package com.example.quizappjc.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizappjc.R
import com.example.quizappjc.presentation.nav_graph.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(
    quizCategory : String,
    onBackClick : ()-> Unit

){
    TopAppBar(modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            actionIconContentColor = colorResource(id = R.color.blue_grey),
            containerColor = colorResource(id = R.color.dark_slate_blue),
            navigationIconContentColor = colorResource(id = R.color.blue_grey)
        ),
        title = {
            Text(
                text = quizCategory,
                color = colorResource(id = R.color.blue_grey),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {

                Icon(painterResource(id = R.drawable.baseline_keyboard_backspace_24) , contentDescription ="" )


            }
        }

    )
}

@Composable
@Preview
fun PrevQuizAppBar(){
    QuizAppBar(quizCategory = "Science") {

    }
}