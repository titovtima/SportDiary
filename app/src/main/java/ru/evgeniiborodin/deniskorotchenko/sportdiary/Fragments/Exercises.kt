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
import android.widget.*
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.exercises.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.MainActivity


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
        data()
        super.onStart()
    }


    fun data(){

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

//        Log.d("watchdata", watchData)

//        Log.d("usermyid", user.uid) // имя пользователя в лог



        myRef.child(user.uid).child("task").child(watchData).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                task = dataSnapshot.value as Map<String,String>? ?: return
                listOfExercises = ArrayList<String>()
                for (value in task!!) {
                    listOfExercises!!.add(value.key+":  "+value.value)
                }
                updateUI()

                val noextext1 = MainActivity.act!!.findViewById<TextView>(R.id.noextext)

                if (listOfExercises!!.isEmpty()){
                    noextext1.visibility = View.VISIBLE
                } else {
                    noextext1.visibility = View.INVISIBLE
                }
            }



            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        list1234.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                list1234.setSelection(position)
                var texts = (view as TextView).text.toString()
                var i = 0
                while ((texts[i] > '9')||(texts[i] < '0'))
                    i++
                InExercise.exName = texts.substring(0, i-3)
                InExercise.exKol = texts.substring(i)
                MainActivity.act!!.onList()
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