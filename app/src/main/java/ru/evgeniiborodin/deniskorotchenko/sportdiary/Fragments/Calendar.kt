package ru.evgeniiborodin.deniskorotchenko.sportdiary.Fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.calendar.*
import ru.evgeniiborodin.deniskorotchenko.sportdiary.R

class Calendar : Fragment() {

    companion object {
        fun newInstance(): Calendar {
            return Calendar()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) return null

        var firstweekday = 6
        var koldays = 31

        var mp = LinearLayout.LayoutParams.MATCH_PARENT
        var wc = LinearLayout.LayoutParams.WRAP_CONTENT

//        var infl = inflater.inflate(R.layout.calendar, container, false)

        var ll1 = LinearLayout(container.context)
        ll1.orientation = LinearLayout.VERTICAL

        var ll2 = LinearLayout(container.context)
        ll2.orientation = LinearLayout.HORIZONTAL
        ll2.weightSum = 7f
        var lp = LinearLayout.LayoutParams(wc, wc)
        lp.weight = 1f
        for (i in 1..firstweekday){
            var text = TextView(container.context)
            ll2.addView(text, lp)
        }

//        var numb = 1

        for(numb in 1..koldays){
            var text = TextView(container.context)
            text.text = numb.toString()
            lp = LinearLayout.LayoutParams(wc, wc)
            lp.weight = 1f
            ll2.addView(text, lp)
            if ((numb + firstweekday)% 7 == 0){
                lp = LinearLayout.LayoutParams(mp, wc)
                lp.weight = 1f
                ll1.addView(ll2, lp)
                ll2 = LinearLayout(container.context)
                ll2.orientation = LinearLayout.HORIZONTAL
                ll2.weightSum = 7f
            }
        }

        if ((koldays + firstweekday)%7 != 0){
            lp = LinearLayout.LayoutParams(wc, wc)
            lp.weight = 1f
            for (i in (koldays + firstweekday)%7..7){
                var text = TextView(container.context)
                ll2.addView(text, lp)
            }
            lp = LinearLayout.LayoutParams(mp, wc)
            lp.weight = 1f
            ll1.addView(ll2, lp)
        }

        return ll1
//        return infl
    }
}