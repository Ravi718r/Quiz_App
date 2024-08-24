package com.example.quizappjc.presentation.score

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimatable
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizappjc.presentation.util.Dimens
import com.example.quizappjc.R

@Composable
@Preview
fun PrevScoreScreen() {
    ScoreScreen(numOfQuestions = 10, numOfCorrectans = 5)
}

@Composable

fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectans: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "close",
                    tint = colorResource(id = R.color.blue_grey)
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.MediumPadding,
                    vertical = Dimens.MediumPadding
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.congra))
                val annotedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("You attempted")
                    }

                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("$numOfQuestions questions ")
                    }

                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("and from that ")
                    }

                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("$numOfCorrectans answers ")
                    }

                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("are correct")
                    }
                }
                val scorePercentage = calculatePercentage(k = numOfCorrectans, n = numOfQuestions)
                LottieAnimation(
                    modifier = Modifier.size(50.dp),
                    composition = composition,
                    iterations = 100
                )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "$scorePercentage% Score ",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "Quiz completed successfully. ",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = annotedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.LargerSpacerHeight))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Share with us : ",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.SmallTextSize,
                    )
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = ""
                    )
//                    Spacer(modifier = Modifier.width(Dimens.SmallSpaceWidth))
//
//                    Icon(
//                        modifier = Modifier
//                            .size(30.dp),
//                        painter = painterResource(id = R.drawable.instagram),
//                        contentDescription = ""
//                    )
//                    Spacer(modifier = Modifier.width(Dimens.SmallSpaceWidth))
//
//                    Icon(
//                        modifier = Modifier
//                            .size(30.dp),
//                        painter = painterResource(id = R.drawable.instagram),
//                        contentDescription = ""
//                    )
                }

            }
        }
    }
}


@Composable
fun calculatePercentage(k: Int, n: Int): Double {
    require(k >= 0 && n > 0) { "Invalid input : k must be non-negative and n must be positive" }
    val percentage = (k.toDouble() / n.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}