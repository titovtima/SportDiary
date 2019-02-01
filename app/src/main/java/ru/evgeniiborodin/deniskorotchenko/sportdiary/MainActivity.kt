package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.exercises,Exercises())
                .commit()
        }
    }

    /*fun onExercises(view: View) {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, exercises)
        transaction.commit()
    }

    fun onStatistics(view: View) {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, exercises)
        transaction.commit()
    }*/


}

