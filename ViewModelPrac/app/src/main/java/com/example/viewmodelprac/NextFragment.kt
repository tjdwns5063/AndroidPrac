package com.example.viewmodelprac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.viewmodelprac.databinding.FragmentNextBinding

class NextFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNextBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_next, container, false)
        binding.prevBtn.setOnClickListener {
            findNavController().navigate(R.id.action_nextFragment_to_titleFragment)
        }
        return binding.root
    }
}