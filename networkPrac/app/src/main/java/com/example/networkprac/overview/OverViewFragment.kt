package com.example.networkprac.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkprac.R
import com.example.networkprac.databinding.FragmentOverViewBinding
import com.example.networkprac.network.Boxoffice

class OverViewFragment : Fragment() {
    private val viewModel: OverViewModel by lazy {
        ViewModelProvider(this).get(OverViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverViewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.button.setOnClickListener {
            viewModel.dateIsClicked(binding.editDate)
        }

        val adapter = MovieAdapter(MovieAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.recyclerView.adapter = adapter
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}