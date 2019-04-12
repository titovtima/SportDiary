package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.calendar.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.MainActivity
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class CalendarF : Fragment() {

    companion object {
        fun newInstance(): CalendarF {
            return CalendarF()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) return null

        var infl = inflater.inflate(R.layout.calendar, container, false)

        var calendar = infl.findViewById<CalendarView>(R.id.calenv)
        calendar.date = MainActivity.mcalendar.timeInMillis
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            if (MainActivity.act != null) {
            (MainActivity.act as MainActivity).outCalendar(view, year, month, dayOfMonth)
//            }
        }

//        Log.d("calendardata", calendar.date.toString())


///////////////////////////////////////////////////////////////
//        var firstweekday = 6
//        var koldays = 31
//
//        var mp = LinearLayout.LayoutParams.MATCH_PARENT
//        var wc = LinearLayout.LayoutParams.WRAP_CONTENT
//
//
//        var ll1 = LinearLayout(container.context)
//        ll1.orientation = LinearLayout.VERTICAL
//
//        var ll2 = LinearLayout(container.context)
//        ll2.orientation = LinearLayout.HORIZONTAL
//        ll2.weightSum = 7f
//        var lp = LinearLayout.LayoutParams(wc, wc)
//        lp.weight = 1f
//        for (i in 1..firstweekday){
//            var text = TextView(container.context)
//            ll2.addView(text, lp)
//        }
//
////        var numb = 1
//
//        for(numb in 1..koldays){
//            var text = TextView(container.context)
//            text.text = numb.toString()
//            lp = LinearLayout.LayoutParams(wc, wc)
//            lp.weight = 1f
//            ll2.addView(text, lp)
//            if ((numb + firstweekday)% 7 == 0){
//                lp = LinearLayout.LayoutParams(mp, wc)
//                lp.weight = 1f
//                ll1.addView(ll2, lp)
//                ll2 = LinearLayout(container.context)
//                ll2.orientation = LinearLayout.HORIZONTAL
//                ll2.weightSum = 7f
//            }
//        }
//
//        if ((koldays + firstweekday)%7 != 0){
//            lp = LinearLayout.LayoutParams(wc, wc)
//            lp.weight = 1f
//            for (i in (koldays + firstweekday)%7..7){
//                var text = TextView(container.context)
//                ll2.addView(text, lp)
//            }
//            lp = LinearLayout.LayoutParams(mp, wc)
//            lp.weight = 1f
//            ll1.addView(ll2, lp)
//        }
//
//        return ll1
////////////////////////////////////////////////////////////////

        return infl
    }
}