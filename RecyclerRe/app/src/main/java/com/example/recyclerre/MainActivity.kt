package com.example.recyclerre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerre.database.MyDatabase
import com.example.recyclerre.database.MyDatabaseDAO
import com.example.recyclerre.database.MyModel
import com.example.recyclerre.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MyDatabaseDAO
    lateinit var itemList: List<MyModel>
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MyDatabase.getInstance(this).myDatabaseDAO
        myAdapter = MyAdapter()
        initAdapter()
        binding.addButton.setOnClickListener { onAdd() }
        binding.clearButton.setOnClickListener { onClear() }
    }

    fun initAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myAdapter.submitList(itemList)
                binding.recyclerView.adapter = myAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext,
                    LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    fun onAdd() {
        val model = MyModel(binding.editText.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            insert(model)
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myAdapter.submitList(itemList)
                binding.editText.text.clear()
            }
        }
    }

    fun onClear() {
        CoroutineScope(Dispatchers.IO).launch {
            clear()
            itemList = getAll()
            CoroutineScope(Dispatchers.Main).launch {
                myAdapter.submitList(itemList)
            }
        }
    }

    suspend fun insert(myModel: MyModel) {
        db.insert(myModel)
        Log.i("RECYCLER", "INSERT CALLED")
    }
    suspend fun getAll(): List<MyModel> {
        Log.i("RECYCLER", "GET ALL CALLED")
        return (db.getAll())
    }
    suspend fun clear() {
        Log.i("RECYCLER", "CLEAR CALLED")
        db.clear()
    }
}