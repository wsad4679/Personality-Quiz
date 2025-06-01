package com.example.personalityquiz

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.LocalTime

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_summary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dateOfQuiz = LocalDate.now()
        val timeOfQuiz = LocalTime.now()

        val dateTextView : TextView = findViewById(R.id.dateOfQuiz)

        val personalityImage : ImageView = findViewById(R.id.personalityImageView)

        dateTextView.text = "Data skończenia quizu: ${dateOfQuiz}  ${timeOfQuiz}"



        val quizDataPoints = intent.getIntExtra("QUIZ_DATA_POINTS", 0)
        val quizDataGender = intent.getStringExtra("QUIZ_DATA_GENDER")
        val quizDataColor = intent.getStringExtra("QUIZ_DATA_COLOR")
        val personality : TextView = findViewById(R.id.personalityTextView)

        if (quizDataGender == "Kobieta") {
            if (quizDataPoints > 6) {
                personality.text= "Jesteś ekstrawertyczką a twój ulubiony kolor to $quizDataColor"
                personalityImage.setImageResource(R.drawable.ekstrawerty)
            } else {
                personality.text= "Jesteś introwertyczką a twój ulubiony kolor to $quizDataColor"
                personalityImage.setImageResource(R.drawable.introwertyk)
            }
        } else if (quizDataGender == "Mężczyzna") {
            if (quizDataPoints > 6) {
                personality.text= "Jesteś ekstrawertykiem a twój ulubiony kolor to $quizDataColor"
                personalityImage.setImageResource(R.drawable.ekstrawerty)
            } else {
                personality.text= "Jesteś introwertykiem a twój ulubiony kolor to $quizDataColor"
                personalityImage.setImageResource(R.drawable.introwertyk)
            }
        }
    }
}