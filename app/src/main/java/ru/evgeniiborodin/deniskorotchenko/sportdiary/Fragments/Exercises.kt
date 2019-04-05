package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser


//1
class Exercises : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference
    private var mRecyclerView: RecyclerView? = null
    var listOfExercises: ArrayList<String>? = null
    var task: Map<String,String>? = null

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


    override fun onStart() {
        super.onStart()
        data()
    }


    fun data(){

        myRef = FirebaseDatabase.getInstance().reference

        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser = auth.currentUser!! // получили нынешнего пользователя



        myRef.child(user.uid).child("task").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                task = dataSnapshot.value as Map<String,String>? ?: return
                listOfExercises = ArrayList<String>()
                for (value in task!!) {
                    listOfExercises!!.add(value.key+":  "+value.value)
                }
                val toast1 = Toast.makeText(
                    context,
                    listOfExercises!!.get(1), Toast.LENGTH_SHORT
                )
                toast1.show()
                updateUI()
            }



            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


    }

    fun updateUI() {
        var listOfData: ListView = activity!!.findViewById(R.id.list1234)


        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1, listOfExercises
        )

        listOfData.adapter = adapter

    }

}