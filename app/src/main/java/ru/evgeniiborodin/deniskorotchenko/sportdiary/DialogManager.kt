package ru.evgeniiborodin.deniskorotchenko.sportdiary

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

public class DialogManager {
    companion object {
        var IDD_ADDDIALOG : Int = 1
    }
    public fun getDialog(activity : Activity, ID : Int) : AlertDialog {
        var builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        when(ID){
            IDD_ADDDIALOG -> {
                builder.setTitle("Добавление приложения")
                    .setCancelable(true)
                    .setNegativeButton("OK") { dialog, id -> dialog.cancel() }
                return builder.create()
            }
        }
        return builder.create()
    }
}