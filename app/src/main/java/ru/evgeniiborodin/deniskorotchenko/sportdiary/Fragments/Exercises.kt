package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser


//1
class Exercises : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var myRef: DatabaseReference
    private var mRecyclerView: RecyclerView? = null
    var ListOfData: ListView? = null
    private val mLayoutManager: RecyclerView.LayoutManager? = null
    var task: Map<String,String>? = null

    //2
    companion object {

        fun newInstance(): Exercises {
            return Exercises()
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        data()

        return inflater.inflate(R.layout.exercises, container, false)
    }





    fun data(){
        ListOfData = activity?.findViewById(R.id.list) // ListView где будет отображаться вся информация

        myRef = FirebaseDatabase.getInstance().reference

        auth = FirebaseAuth.getInstance()
        var user: FirebaseUser = auth.currentUser!! // получили нынешнего пользователя



        myRef.child(user.uid).child("task").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var t: GenericTypeIndicator<String>  =   GenericTypeIndicator<String>()
                task = dataSnapshot.value as Map<String,String>?
                /*val toast = Toast.makeText(
                    context,
                    task!!.size, Toast.LENGTH_SHORT
                )
                toast.show()*/
                val toast = Toast.makeText(
                    context,
                    task?.size.toString(), Toast.LENGTH_SHORT
                )
                toast.show()
                //updateUI()
            }



            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun updateUI() {
        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1)

        ListOfData?.adapter = adapter
        /*val toast = Toast.makeText(
            context,
            task, Toast.LENGTH_SHORT
        )
        toast.show()*/
    }

}