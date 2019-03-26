package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.reg.*
import kotlinx.android.synthetic.main.auth.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Auth
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Registration
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Statistics
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase






class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Auth.newInstance())
                .addToBackStack(null)
                .commit()

        }
        else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, Exercises.newInstance())
                .commit()
        }
    }


    fun onReg(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Registration.newInstance())
            .commit()
    }


    fun reg(view: View){
        var email: String = email.text.toString()
        var password: String = password.text.toString()
        val password_conf: String = password_confirm.text.toString()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when {
                    password_conf != password -> {
                        var editText: EditText = findViewById(R.id.password)
                        var editText1: EditText = findViewById(R.id.password_confirm)
                        editText.text = null
                        editText1.text = null
                    }
                    task.isSuccessful -> {
                        val toast = Toast.makeText(
                            applicationContext,
                            "Вы успешно зарегестрировались!", Toast.LENGTH_SHORT
                        )
                        toast.show()
                        auth = FirebaseAuth.getInstance()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, Exercises.newInstance())
                            .commit()
                    }
                    else -> {
                        val toast = Toast.makeText(
                            applicationContext,
                            "Видимо такой email уже используется или вы не подключены к интернету" +
                                    ", повторите попытку позднее или используйте другой адрес", Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
    }

    fun auth(view: View) {
        val auth = FirebaseAuth.getInstance()
        var email: String = email1.text.toString()
        var password: String = password1.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Вы успешно вошли в свой аккаунт!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,Exercises.newInstance())
                        .commit()
                }
                else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Неправильная пара логин/пароль или отсутствие подключения " +
                                "к интернету", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            })
    }

    fun out(view: View) {
        FirebaseAuth.getInstance().signOut()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Auth.newInstance())
            .commit()
    }

    fun exercise(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, Exercises.newInstance())
            .commit()
    }

    fun onStatistics(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Statistics.newInstance())
            .commit()
    }
}

