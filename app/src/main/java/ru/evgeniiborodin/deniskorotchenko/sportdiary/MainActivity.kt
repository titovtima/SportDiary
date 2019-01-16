package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    var exercises: Exercises? = null
    var transaction: android.app.FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exercises = Exercises()


        var transaction = fragmentManager.beginTransaction()
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
        transaction.replace(R.id.container, exercises)
        transaction.commit()
    }


}
