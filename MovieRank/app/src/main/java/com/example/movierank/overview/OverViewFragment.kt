package com.example.movierank.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierank.FragmentId
import com.example.movierank.MainActivity
import com.example.movierank.R
import com.example.movierank.databinding.FragmentOverViewBinding
import com.example.movierank.viewmodel.SharedViewModel

class OverViewFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentOverViewBinding
    private var date: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        viewModel = mainActivity.getSharedViewModel()
        date = viewModel.getDate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_over_view, container, false)
        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this.context, LinearLayoutManager.VERTICAL, false)
        viewModel.getMovieData().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.button.setOnClickListener { mainActivity.moveFragment(FragmentId.Title) }
        return binding.root
    }
}
