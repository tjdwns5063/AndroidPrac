package com.example.loginprac.sign

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.loginprac.R
import com.example.loginprac.databinding.FragmentSignBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignBinding.inflate(layoutInflater)
        binding.signInBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_signFragment_to_signInFragment)
        }
        auth = Firebase.auth
        val context = requireActivity().applicationContext
        binding.loginBtn.setOnClickListener { onClickLogin(context) }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_signFragment_to_resultFragment)
        }
    }

    private fun onClickLogin(context: Context) {
        if (checkId(context) && checkPassword(context)) {
            login(context)
        }
    }

    private fun login(context: Context) {
        val email = binding.idEditText.text
        val password = binding.pwEditText.text

        auth.signInWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(R.id.action_signFragment_to_resultFragment)
                } else {
                    Toast.makeText(context, "로그인에 실패했습니다 ${it.exception.toString()}.", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun checkId(context: Context): Boolean {
        if (binding.idEditText.text.isEmpty()) {
            Toast.makeText(context, "아이디를 입력해주세요.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        return (true)
    }

    private fun checkPassword(context: Context): Boolean {
        if (binding.pwEditText.text.isEmpty()) {
            Toast.makeText(context, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        return (true)
    }
}