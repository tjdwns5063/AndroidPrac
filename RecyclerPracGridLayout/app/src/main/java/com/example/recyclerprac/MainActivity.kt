package com.example.recyclerprac

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerprac.database.MyDatabase
import com.example.recyclerprac.database.MyDatabaseDAO
import com.example.recyclerprac.database.MyModel
import com.example.recyclerprac.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var itemList = listOf<MyModel>()
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    private lateinit var db: MyDatabaseDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        db = MyDatabase.getInstance(this).myDatabaseDAO
        myRecyclerAdapter = MyRecyclerAdapter(MyModelListener { nameId ->
            Toast.makeText(applicationContext, "${nameId}", Toast.LENGTH_SHORT).show()
        })
        setContentView(binding.root)

        initAdapter()
        binding.clearButton.setOnClickListener { onClickClear() }
        binding.button.setOnClickListener { onClickAdd() }
    }

    private fun onClickAdd() {
        CoroutineScope(Dispatchers.IO).launch {
            insert(MyModel("seongjki"))
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myRecyclerAdapter.submitList(itemList)
            }
        }
    }

    private fun onClickClear() {
        CoroutineScope(Dispatchers.IO).launch {
            clear()
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myRecyclerAdapter.submitList(itemList)
            }
        }
    }

    private fun initAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myRecyclerAdapter.submitList(itemList)
                binding.myRecyclerView.adapter = myRecyclerAdapter
                binding.myRecyclerView.layoutManager = GridLayoutManager(applicationContext,
                    3, GridLayoutManager.VERTICAL, false)
            }
        }
    }

    private suspend fun clear() {
        Log.i("RECYCLE", "CLEAR CALLED")
        db.clear()
    }
    private suspend fun insert(item: MyModel) {
        Log.i("RECYCLE", "INSERT ${item.name} CALLED")
        db.insert(item)
    }
    private suspend fun getAll(): List<MyModel> {
        Log.i("RECYCLE", "GET ALL CALLED")
        return (db.getAll())
    }
    private suspend fun getName(): MyModel {
        return (db.getName())
    }
}