package ru.evgeniiborodin.deniskorotchenko.sportdiary


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.reg.*
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
                .addToBackStack(null)
                .commit()
        }
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
                        "Видимо такой email уже используется, повторите попытку снова, используя другой адрес" +
                                " электронной почты", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
    }

}

