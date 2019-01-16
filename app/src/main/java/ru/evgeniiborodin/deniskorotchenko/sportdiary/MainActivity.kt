package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Statistics


class MainActivity : AppCompatActivity() {

    private lateinit var exercises: Exercises
    private lateinit var statistics: Statistics
    private lateinit var transaction: android.app.FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        exercises = Exercises()
        statistics = Statistics()


        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, exercises)
        transaction.commit()
    }

    fun onExercises(view: View) {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, exercises)
        transaction.commit()
    }

    fun onStatistics(view: View) {
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, statistics)
        transaction.commit()
    }


}

