package ru.evgeniiborodin.deniskorotchenko.sportdiary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        change.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        // supportActionBar!!.hide()
        supportActionBar!!.title = Date().toString()

        val listView = findViewById<View>(R.id.listView) as ListView

        val sportActivity = arrayOf(
            "Бег",
            "Отжимания",
            "Приседания",
            "Подтягивания"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, sportActivity
        )

        listView.adapter = adapter
    }

}
