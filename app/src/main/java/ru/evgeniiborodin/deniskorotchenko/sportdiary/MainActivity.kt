package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Statistics


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container,Exercises.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    fun onExercises(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Exercises.newInstance())
            .commit()
    }

    fun onStatistics(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Statistics.newInstance())
            .commit()
    }


}

