package com.example.movierank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.movierank.databinding.ActivityMainBinding
import com.example.movierank.overview.OverViewFragment
import com.example.movierank.viewmodel.SharedViewModel

enum class FragmentId {
    Title,
    OverView
}

class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    val viewModel: SharedViewModel by lazy { getSharedViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val title = TitleFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.parent_view, title)
        transaction.commit()
        setContentView(binding.root)
    }

    fun moveFragment(id: FragmentId) {
        when (id) {
            FragmentId.Title -> transactionTitle()
            FragmentId.OverView -> transactionOverView()
            }
        }
    private fun transactionTitle() {
        val title = TitleFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.parent_view, title)
        transaction.commit()
    }

    private fun transactionOverView() {
        val overview = OverViewFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.parent_view, overview)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun getSharedViewModel(): SharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
}

