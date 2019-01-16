package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class Exercises : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val productsView = inflater.inflate(R.layout.exercises, container, false)
        return productsView
    }

    fun onButton(view: View) {
        val toast = Toast.makeText(
            activity!!.applicationContext,
            "Заработало", Toast.LENGTH_SHORT
        )
        toast.show()
    }
}