package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class Statistics : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val productsView = inflater.inflate(R.layout.exercises, container, false)
        return productsView
    }

}