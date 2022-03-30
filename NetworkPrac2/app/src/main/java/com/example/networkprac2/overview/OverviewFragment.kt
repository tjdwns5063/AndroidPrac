package com.example.networkprac2.overview

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.networkprac2.R
import com.example.networkprac2.databinding.FragmentOverviewBinding
import java.util.zip.Inflater

class OverviewFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this // 까먹지 말자.. 뷰모델이 지금 이 프래그먼트의 라이프 사이클을 따라가게 만들어줌
        binding.viewModel = viewModel
        binding.todayButton.setOnClickListener { viewModel.onTodayClick() }
        binding.tomorrowButton.setOnClickListener { viewModel.onTomorrowClick() }
        binding.yesterdayButton.setOnClickListener { viewModel.onYesterdayClick() }
        val adapter = MovieAdapter(MovieAdapter.OnClickListener{
            viewModel.displayDetailProperty(it)
        })
        binding.recyclerView.adapter = adapter
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayDetailComplete()
            }
        })
        return (binding.root)
    }
}