package com.joincoded.task_androidcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joincoded.task_androidcomposeui.ui.theme.TaskAndroidComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAndroidComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CorrectWrongGame()
                }
            }
        }
    }
}

@Composable
fun CorrectWrongGame(modifier: Modifier = Modifier) {
    var questions = listOf("7-5 = 10", "30-100 = 90", "15+15 = 30", "10+6 = 15", "5+5 = 10", "3+3 = 7")


    var answers = listOf(false, false, true, false, true, false)


    var score by remember {
        mutableStateOf(0)
    }


    var currentQuestionIndex by remember {
        mutableStateOf(0)

    }


    var showCorrectResult by remember {
        mutableStateOf(false)
    }



    var showWrongResult by remember {
        mutableStateOf(false)
    }



    var showNextQuestionButton by remember {
        mutableStateOf(false)
    }



    var showAnswersOptionsRow by remember {
        mutableStateOf(true)
    }



    Column(
        modifier = modifier.padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = questions[currentQuestionIndex],
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )



        if (showWrongResult) {
            WrongAnswer(text = "Wrong Answer")
        }

        if (showCorrectResult) {
            CorrectAnswer(text = "Correct Answer")
        }

        Text(text = "Score: $score", fontSize = 50.sp)

        if (showNextQuestionButton)
            Button(onClick = {
                if (currentQuestionIndex == questions.size - 1) {
                    currentQuestionIndex = 0
                    score = 0
                } else
                    currentQuestionIndex++
                showNextQuestionButton = false
                showAnswersOptionsRow = true
                showCorrectResult = false
                showWrongResult = false

            }) {
                Text(text = "Next Question")
            }
        if (showAnswersOptionsRow)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                Alignment.Bottom
            ) {

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isCorrect = true == answers[currentQuestionIndex]
                        if (isCorrect) {
                            score++
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true
                            showNextQuestionButton = false
                        }
                    }) {
                    Text(text = "True")
                }
                Spacer(modifier = Modifier.width(50.dp))

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isCorrect = false == answers[currentQuestionIndex]
                        if (isCorrect) {
                            score++
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true
                            showNextQuestionButton = false

                        }
                    }) {
                    Text(text = "False")
                }
            }
    }

}

@Composable
fun CorrectAnswer(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(150.dp)
            .background(Color.Green),
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@Composable
fun WrongAnswer(text: String, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(150.dp)
            .background(Color.Red),
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}