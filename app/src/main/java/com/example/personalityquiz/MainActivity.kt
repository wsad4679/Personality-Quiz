package com.example.personalityquiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Chronometer
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val stopWatch : Chronometer = findViewById(R.id.quizTimeChronometer)
        val counDownTimer : TextView = findViewById(R.id.countDownTimer)

        val firstQuestion : CheckBox = findViewById(R.id.firstQuestionCheckBox)
        val secondQuestion : CheckBox = findViewById(R.id.secondQuestionCheckBox)
        val thirdQuestion : CheckBox = findViewById(R.id.thirdQuestionCheckBox)


        var firstAnswer = 0
        var secondAnswer = 0
        var thirdAnswer = 0

        firstQuestion.setOnCheckedChangeListener { _, isChecked ->
            firstAnswer = if (isChecked)  1 else 0
        }
        secondQuestion.setOnCheckedChangeListener { _, isChecked ->
            secondAnswer = if (isChecked)  1 else 0
        }
        thirdQuestion.setOnCheckedChangeListener { _, isChecked ->
            thirdAnswer = if (isChecked)  1 else 0
        }
        
        
        




        
        val genderRadioGroup : RadioGroup = findViewById(R.id.genderRadioGroup)
        
        genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val genderRadioButton : RadioButton = findViewById(checkedId)
            val gender = "${genderRadioButton.text}"
        }


        val spinner : Spinner = findViewById(R.id.favouriteColorSpinner)

        val colors = arrayOf("czerwony", "niebieski", "zielony", "różowy", "biały", "fioletowy", "czarny")
        lateinit var selectedColor : String
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedColor = colors[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedColor = colors[0]
            }
        }



        val finishButton : Button = findViewById(R.id.finishTestButton)

        finishButton.setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            val data = "przyszłe dane"
            intent.putExtra("QUIZ_DATA", data)
            startActivity(intent)
        }







    }
}