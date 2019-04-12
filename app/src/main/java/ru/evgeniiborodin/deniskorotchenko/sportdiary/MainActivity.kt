package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.reg.*
import kotlinx.android.synthetic.main.auth.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Auth
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Registration
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Statistics
import kotlinx.android.synthetic.main.exercises.*
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.*
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        var act : MainActivity? = null
//        var calendardate : Long = 0
        var mcalendar : Calendar = Calendar.getInstance()
        var year = 2019
        var month = 4
        var dayOfMonth = 12
    }

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")

        MainActivity.act = this
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Auth.newInstance())
                .addToBackStack(null)
                .commit()
        }
        else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Exercises.newInstance())
                .commit()
        }
    }


    fun onCheckFirebase(view: View){
        DataHandler.getsmth("Exercise 1")
    }


    fun onReg(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Registration.newInstance())
            .commit()
    }


    fun reg(view: View){
        var email: String = email.text.toString()
        var password: String = password.text.toString()
        val password_conf: String = password_confirm.text.toString()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when {
                    password_conf != password -> {
                        var editText: EditText = findViewById(R.id.password)
                        var editText1: EditText = findViewById(R.id.password_confirm)
                        editText.text = null
                        editText1.text = null
                    }
                    task.isSuccessful -> {
                        val toast = Toast.makeText(
                            applicationContext,
                            "Вы успешно зарегестрировались!", Toast.LENGTH_SHORT
                        )
                        toast.show()
                        auth = FirebaseAuth.getInstance()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, Exercises.newInstance())
                            .commit()
                    }
                    else -> {
                        val toast = Toast.makeText(
                            applicationContext,
                            "Видимо такой email уже используется или вы не подключены к интернету" +
                                    ", повторите попытку позднее или используйте другой почтовый адрес", Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
    }

    fun auth(view: View) {
        val auth = FirebaseAuth.getInstance()
        var email: String = email1.text.toString()
        var password: String = password1.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Вы успешно вошли в свой аккаунт!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,Exercises.newInstance())
                        .commit()
                }
                else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Неправильная пара логин/пароль или отсутствие подключения " +
                                "к интернету", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            })
    }

    fun out(view: View) {
        FirebaseAuth.getInstance().signOut()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Auth.newInstance())
            .commit()
    }

    fun exercise(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Exercises.newInstance())
            .commit()
    }

    fun onStatistics(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Statistics.newInstance())
            .commit()
    }

    fun onCalendar(view : View){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,CalendarF.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun outCalendar(view: CalendarView, year: Int, month: Int, dayOfMonth : Int){
        MainActivity.year = year
        MainActivity.month = month + 1
        MainActivity.dayOfMonth = dayOfMonth
        MainActivity.mcalendar.set(Calendar.YEAR, year)
        MainActivity.mcalendar.set(Calendar.MONTH, month)
        MainActivity.mcalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Exercises.newInstance())
            .commit()
    }

    fun onNewExercise(view: View){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,NewExercise.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun outOfNewExercise(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Exercises.newInstance())
            .commit()
    }
}

