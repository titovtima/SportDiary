package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.in_exercise.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.MainActivity
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class InExercise : DialogFragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference

    companion object {
        lateinit var exName : String
        lateinit var exKol : String

        fun newInstance() : InExercise{
            return InExercise()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.in_exercise, container, false)
    }

    override fun onStart() {



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

        text.text = exName + " " + exKol

        fulfilled.setOnClickListener {
            myRef.child(user.uid).child("fulfilled").child(watchData).child(exName).setValue(exKol)
            myRef.child(user.uid).child("task").child(watchData).child(exName).removeValue()
            val toast = Toast.makeText(context, "Поздравляем! Вы выполнили упражнение!", Toast.LENGTH_SHORT)
            toast.show()
            dismiss()
        }

        delete.setOnClickListener {
            myRef.child(user.uid).child("task").child(watchData).child(exName).removeValue()
            dismiss()
        }

        super.onStart()
    }

//    fun onClick(view : View){
//
//    }
}