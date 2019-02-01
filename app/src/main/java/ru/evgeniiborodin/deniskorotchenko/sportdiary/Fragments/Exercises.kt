package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R


//1
class Exercises : Fragment() {

    //2
    companion object {

        fun newInstance(): Exercises {
            return Exercises()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.exercises, container, false)
    }

}