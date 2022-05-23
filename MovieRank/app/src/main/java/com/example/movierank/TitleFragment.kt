package com.example.movierank

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.example.movierank.databinding.FragmentTitleBinding
import com.example.movierank.viewmodel.SharedViewModel
import java.util.*

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    private var date: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            if (compareToday(year, month, day))
                dateToString(year, month, day)
            else
                date = null
        }
        binding.findButton.setOnClickListener {
            if (date != null) {
                Toast.makeText(this.context, date, Toast.LENGTH_SHORT).show()
                Log.i("date", "first: ${date}")
                mainActivity.viewModel.setDate(date)
                mainActivity.moveFragment(FragmentId.OverView)
            }
        }
        return binding.root
    }

    private fun compareToday(year: Int, month: Int, day: Int): Boolean {
        val today = Calendar.getInstance()
        if (day >= today.get(Calendar.DAY_OF_MONTH))
            return false
        else if (month > today.get(Calendar.MONTH))
            return false
        else if (year > today.get(Calendar.YEAR))
            return false
        return true
    }

    private fun dateToString(year: Int, month: Int, day: Int) {
        val y = year.toString()
        val m = if (month < 10) "0${month + 1}" else "${month + 1}"
        val d = if (day < 10) "0$day" else day.toString()
        date = "$y$m$d"
    }
}