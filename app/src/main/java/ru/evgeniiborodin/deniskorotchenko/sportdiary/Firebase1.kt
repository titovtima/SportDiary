//package ru.evgeniiborodin.deniskorotchenko.sportdiary
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//
////import com.google.firebase.database.DataSnapshot
////import com.google.firebase.database.DatabaseError
////import com.google.firebase.database.FirebaseDatabase
////import com.google.firebase.database.ValueEventListener
//
//class Firebase1 : AppCompatActivity() {
//
//    val TAG = "tag"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_firebase1)
//
//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val value = dataSnapshot.getValue(String::class.java)
//                Log.d(TAG, "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
//    }
//}
//
//fun addDays(day : String){
//    val database = FirebaseDatabase.getInstance()
//    val myRef = database.getReference("Days")
//
//    var days : String?
//    myRef.addValueEventListener(object : ValueEventListener{
//        override fun onDataChange(p0: DataSnapshot) {
////            days = p0.getValue()
//        }
//
//        override fun onCancelled(p0: DatabaseError) {
//
//        }
//    })
//}
///*
//
//<activity android:name=".JavaActivity">
//</activity>
//<activity android:name=".Firebase1">
//</activity>
//
//
//    implementation 'com.google.firebase:firebase-database:16.0.1'
//    implementation 'com.google.firebase:firebase-core:16.0.7'
//    implementation 'com.google.firebase:firebase-auth:16.1.0'
//
//
//*/