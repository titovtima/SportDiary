package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.new_exercise.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class NewExercise : Fragment() {

    val listOfAllExercises : ArrayList<String> = arrayListOf("Бег", "Бёрпи", "Велосипед", "Гантели", "Катание на велосипеде",
                        "Отжимания", "Плавание", "Планка", "Подтягивания", "Пресс", "Приседания", "Прыгание через парту",
                        "Прыжки в длину", "Прыжки на скакалке", "Растяжка", "Рывки на 10 метров")
    var selected = "Бег"
    var entered = 0

    companion object {
        fun newInstance(): NewExercise {
            return NewExercise()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_exercise, container, false)
    }

    override fun onStart() {
        super.onStart()

        val spinexadap = ArrayAdapter(context, android.R.layout.simple_list_item_1, listOfAllExercises)
        spinexadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinex.adapter = spinexadap

//        var listener = AdapterView.OnItemSelectedListener(){
//
//        }
        spinex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selected = listOfAllExercises[position]
                when (selected){
                    "Бег", "Катание на велосипеде", "Плавание" -> text.text = "минут"
                    "Велосипед" -> text.text = "км"
                    "Прыжки на скакалке", "Планка" -> text.text = "секунд"
                    else -> text.text = "раз"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

//        Log.d("substring", "message".substring(1, 3))

        times.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                if (times.text.toString() == ""){
                    entered = 0
                } else {
                    entered = times.text.toString().toInt()
                }
//                Log.d("substring2", entered.toString())

                if (entered == 0)
                    times.text.clear()

                if (entered/10 == 1){
                    when (text.text.substring(0, 2)){
                        "се" -> text.text = "секунд"
                        "ра" -> text.text = "раз"
                        "ми" -> text.text = "минут"
                    }
                } else {

                    when (text.text.substring(0, 2)) {
                        "се" -> {
                            when (entered % 10) {
                                1 -> text.text = "секунда"
                                in 2..4 -> text.text = "секунды"
                                else -> text.text = "секунд"
                            }
                        }
                        "ми" -> {
                            when (entered % 10) {
                                1 -> text.text = "минута"
                                in 2..4 -> text.text = "минуты"
                                else -> text.text = "минут"
                            }
                        }
                        "ра" -> {
                            when (entered % 10) {
//                        1 -> text.text = "секунда"
                                in 2..4 -> text.text = "раза"
                                else -> text.text = "раз"
                            }
                        }
                    }
                }
            }
        })

//        times.setOnKeyListener { v, keyCode, event ->
//
//            if (event.action == KeyEvent.ACTION_DOWN) {
//                entered = times.text.toString().toInt()
//                Log.d("substring2", entered.toString())
//                Log.d("substring", text.text.substring(0, 2))
//
//                if (entered/10 == 1){
//                    when (text.text.substring(0, 2)){
//                        "се" -> text.text = "секунд"
//                        "ра" -> text.text = "раз"
//                        "ми" -> text.text = "минут"
//                    }
//                } else {
//
//                    when (text.text.substring(0, 2)) {
//                        "се" -> {
//                            when (entered % 10) {
//                                1 -> text.text = "секунда"
//                                in 2..4 -> text.text = "секунды"
//                                else -> text.text = "секунд"
//                            }
//                        }
//                        "ми" -> {
//                            when (entered % 10) {
//                                1 -> text.text = "минута"
//                                in 2..4 -> text.text = "минуты"
//                                else -> text.text = "минут"
//                            }
//                        }
//                        "ра" -> {
//                            when (entered % 10) {
////                        1 -> text.text = "секунда"
//                                in 2..4 -> text.text = "раза"
//                                else -> text.text = "раз"
//                            }
//                        }
//                    }
//                }
//            }
//
//            false
//        }
    }

}