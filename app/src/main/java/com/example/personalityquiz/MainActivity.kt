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
import android.widget.SeekBar
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
        val counDownTimerTextView : TextView = findViewById(R.id.countDownTimer)


        val genderRadioGroup : RadioGroup = findViewById(R.id.genderRadioGroup)
        var gender = "Kobieta"

        genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val genderRadioButton : RadioButton = findViewById(checkedId)
            gender = "${genderRadioButton.text}"

        }




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



        val firstSeekBar : SeekBar = findViewById(R.id.firstSeekBar)
        val secondSeekBar : SeekBar = findViewById(R.id.secondSeekBar)

        var firsSeekBarNumber : Int = 3
        var secondSeekBarNumber : Int = 3

        firstSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    firsSeekBarNumber = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }


        })

        secondSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                secondSeekBarNumber = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

        })






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


        fun endQuiz(){
            stopWatch.stop()
            val intent = Intent(this, SummaryActivity::class.java)
            val points =("${ firstAnswer + secondAnswer+ thirdAnswer + firsSeekBarNumber + secondSeekBarNumber }").toInt()
            val color = selectedColor
            intent.putExtra("QUIZ_DATA_POINTS", points)
            intent.putExtra("QUIZ_DATA_GENDER", gender)
            intent.putExtra("QUIZ_DATA_COLOR", color)
            startActivity(intent)
        }


        val counDownTimer : CountDownTimer = object : CountDownTimer(10*60*1000, 1){
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished/1000
                counDownTimerTextView.text = "pozostało ${secondsLeft/60}:${secondsLeft%60}"

            }

            override fun onFinish() {
                endQuiz()
            }
        }
        counDownTimer.start()
        stopWatch.start()

        val finishButton : Button = findViewById(R.id.finishTestButton)

        finishButton.setOnClickListener {
            endQuiz()
        }




    }
}