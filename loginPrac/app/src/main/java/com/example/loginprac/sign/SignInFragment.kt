package com.example.loginprac.sign

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.loginprac.R
import com.example.loginprac.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater)
        auth = Firebase.auth
        val context = requireActivity().applicationContext

        binding.button.setOnClickListener {
            popUpSignInTerm()
        }
        binding.signInOkBtn.setOnClickListener {
            onClickSignIn(context)
        }
        binding.signInBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signFragment)
        }
        return binding.root
    }

    private fun checkSign(context: Context): Boolean {
        if (!binding.signInSwitch.isChecked) {
            Toast.makeText(context, "약관에 동의하지 않았습니다.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        return (true)
    }

    private fun checkId(context: Context): Boolean {
        if (binding.signInIdEditText.text.isEmpty()) {
            Toast.makeText(context, "아이디를 입력해주세요.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        return (true)
    }

    private fun checkPassword(context: Context):Boolean {
        if (binding.signInPwEditText.text.isEmpty() || binding.signInPwEditText.text.isEmpty()) {
            Toast.makeText(context, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        else if (binding.signInPwEditText.text.toString() != binding.signInPwCheckEditText.text.toString()) {
            Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG)
                .show()
            return (false)
        }
        return (true)
    }

    private fun createAccount(context: Context) {
        val email = binding.signInIdEditText.text
        val password = binding.signInPwEditText.text

        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "계정 생성 완료", Toast.LENGTH_LONG).show()
                    binding.signInIdEditText.text.clear()
                    binding.signInPwEditText.text.clear()
                    binding.signInPwCheckEditText.text.clear()
                    findNavController().navigate(R.id.action_signInFragment_to_signFragment)
                } else {
                    Toast.makeText(context, "계정 생성 실패", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun onClickSignIn(context: Context) {
        if (checkSign(context) && checkId(context) && checkPassword(context)) {
            createAccount(context)
        }
    }

    private fun popUpSignInTerm() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("회원약관")
        builder.setIcon(R.mipmap.ic_launcher)
        val customView = layoutInflater.inflate(R.layout.fragment_sign_term, null)
        builder.setView(customView)
        builder.setPositiveButton("OK") { dialog, _ ->
            if (!binding.signInSwitch.isChecked) {
                binding.signInSwitch.performClick()
            }
            dialog.cancel()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }
}