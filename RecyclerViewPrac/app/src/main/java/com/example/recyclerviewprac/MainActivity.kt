package com.example.recyclerviewprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewprac.database.NameDatabase
import com.example.recyclerviewprac.database.NameEntity
import com.example.recyclerviewprac.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NameDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = NameDatabase.getInstance(applicationContext)
        setContentView(binding.root)
//        CoroutineScope(Dispatchers.IO).launch {
//            initializeView(database.nameDatabseDAO.getAll())
//        }
        binding.button.setOnClickListener {
            if (binding.editText.text.toString() != "") {
                addName()
                val adapter = NameAdapter(this, getAll())
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            binding.editText.text.clear()
        }
    }

    private fun addName() {
        val name = binding.editText.text.toString()

        database.nameDatabseDAO.insert(NameEntity(name))
        //initializeView(database.nameDatabseDAO.getAll())
    }

    private fun getAll(): List<NameEntity> {
        return (database.nameDatabseDAO.getAll())
    }


    private fun getName(): NameEntity {
        var name = getName()
        return (name)
    }
/*
    fun initializeView(nameData: List<NameEntity>) {

        //binding.textView.text = nameList
    }*/
}