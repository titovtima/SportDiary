//package ru.evgeniiborodin.deniskorotchenko.sportdiary;
//
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.*;
//
//import java.util.List;
//
//public class JavaActivity extends AppCompatActivity {
//
//    private DatabaseReference myRef;
//    private FirebaseAuth mAuth;
//
//    List<String> mlist;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_java);
//
//        myRef = FirebaseDatabase.getInstance().getReference();
//
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>(){};
//                mlist = dataSnapshot.child("Exercises").getValue(t);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
