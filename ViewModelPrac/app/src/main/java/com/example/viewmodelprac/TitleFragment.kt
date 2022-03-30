package com.example.viewmodelprac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.viewmodelprac.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding
    lateinit var viewModel: TitleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.titleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.currentNumb.observe(viewLifecycleOwner, Observer { newNumb ->
            binding.numText.text = newNumb.toString()
        })
        //binding.UpBtn.setOnClickListener { viewModel.onUp() }
        binding.button.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_nextFragment)
        }
        return binding.root
    }
}