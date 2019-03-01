package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.reg.*
import kotlinx.android.synthetic.main.auth.*
import kotlinx.android.synthetic.main.exercises.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Auth
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Exercises
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Registration
import ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments.Statistics
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, Auth.newInstance())
                .addToBackStack(null)
                .commit()
        }
        else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, Exercises.newInstance())
                .commit()
        }
    }


    fun onCheckFirebase(view: View){
        DataHandler.getsmth("Exercise 1")
        Toast.makeText(applicationContext, DataHandler.strData, Toast.LENGTH_SHORT).show()
    }


    fun onReg(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Registration.newInstance())
            .commit()
    }

    fun onStatistics(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,Statistics.newInstance())
            .commit()
    }


    fun reg(view: View){
        var email: String = email.text.toString()
        var password: String = password.text.toString()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Вы успешно зарегестрировались!", Toast.LENGTH_SHORT
                    )
                    toast.show()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,Statistics.newInstance())
                        .commit()
                }
                else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Видимо такой email уже используется или вы не подключены к интернету" +
                                ", повторите попытку снова позднее , используя другой адрес", Toast.LENGTH_SHORT
                    )
                    toast.show()
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
                        .replace(R.id.container,Statistics.newInstance())
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
}

