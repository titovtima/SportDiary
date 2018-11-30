package ru.evgeniiborodin.deniskorotchenko.sportdiary

import android.app.ActionBar
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId){
                android.R.id.home -> {
                    this.finish()
                    return false
                }
                else -> return true
            }
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        addButton.setOnClickListener {
            val dialog = DialogManager().getDialog(this,  DialogManager.IDD_ADDDIALOG)
            dialog.show()
        }
        addAnotherButton.setOnClickListener{
            Toast.makeText(this, "Здесь мы будем открывать какое-то окошко", Toast.LENGTH_LONG).show()
        }

    }
}
