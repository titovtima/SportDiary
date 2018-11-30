package ru.evgeniiborodin.deniskorotchenko.sportdiary

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        addButton.setOnClickListener {
            val dialog = DialogManager().getDialog(this,  DialogManager.IDD_ADDDIALOG)
            dialog.show()
        }
        addAnotherButton.setOnClickListener{
            Toast.makeText(this, "Здесь мы будем открывать какое-то окошко", Toast.LENGTH_LONG).show()
        }

    }
}
