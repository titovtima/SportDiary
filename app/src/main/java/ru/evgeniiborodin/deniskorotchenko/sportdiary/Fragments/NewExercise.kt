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
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.new_exercise.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.MainActivity
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class NewExercise : Fragment() {

    val listOfAllExercises : ArrayList<String> = arrayListOf("Бег", "Бёрпи", "Велосипед", "Гантели", "Катание на велосипеде",
                        "Отжимания", "Плавание", "Планка", "Подтягивания", "Пресс", "Приседания", "Прыгание через парту",
                        "Прыжки в длину", "Прыжки на скакалке", "Растяжка", "Рывки на 10 метров")
    var selected = "Бег"
    var entered = 0


    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference

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

        myRef = FirebaseDatabase.getInstance().reference

        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser = auth.currentUser!! // получили нынешнего пользователя

        var watchData = MainActivity.year.toString()
        if (MainActivity.month < 10)
            watchData += "0"
        watchData += MainActivity.month.toString()
        if (MainActivity.dayOfMonth < 10)
            watchData += "0"
        watchData += MainActivity.dayOfMonth.toString()

        enter.setOnClickListener {
            if (entered > 0) {
                myRef.child(user.uid).child("task").child(watchData).child(selected)
                       .setValue(entered.toString() + " " + text.text.toString())
                MainActivity.act!!.outOfNewExercise()
            } else {
                val toast = Toast.makeText(context, "Введите ненулевое количество упражнений", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

//    Надо придумать, как при входе сразу определять дату, а то пока ты не зашёл в календарь он её не знает.
//    И ещё, как сделать, чтобы календарь подсвечивал ту дату, которую мы выберем, а не всегда сегодня

}