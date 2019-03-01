package ru.evgeniiborodin.deniskorotchenko.sportdiary


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.ArithmeticException

class DataHandler {

    companion object {
        var strArrData : Array<String> = emptyArray()
        var strData : String = "nonononononononononononononono"

        fun getsmth(from : String){
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference(from)

            myRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val res = p0.getValue(String::class.java)
                    if (res == null){
                        strData = "acjdahvljh"
                        return
                    }
                    strData = res
                }

                override fun onCancelled(p0: DatabaseError) {
                    strData = "nonono"
//                val ex = ArithmeticException()
//                throw ex
                }
            })
        }
    }
}