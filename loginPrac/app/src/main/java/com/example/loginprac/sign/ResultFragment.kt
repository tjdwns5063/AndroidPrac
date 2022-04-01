package com.example.loginprac.sign

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.loginprac.R
import com.example.loginprac.databinding.FragmentResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater)
        auth = Firebase.auth
        val context = requireActivity().applicationContext

        binding.currentIdText.text = auth.currentUser?.email.toString()
        binding.logoutBtn.setOnClickListener { onClickLogout(context) }
        return binding.root
    }

    private fun onClickLogout(context: Context) {
        logout(context)
    }

    private fun logout(context: Context) {
        auth?.signOut()
        Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_resultFragment_to_signFragment)
    }
}