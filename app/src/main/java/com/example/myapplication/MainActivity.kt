package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entryRecyclerView = findViewById<RecyclerView>(R.id.list)
        val entries = mutableListOf<InputData>()
        val adapter = EntryAdapter(this, entries)
        entryRecyclerView.adapter = adapter
        entryRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch{
            //(application as ArticleApplication).db.inputDao().deleteAll()
            (application as ArticleApplication).db.inputDao().getAll().collect{databaseList ->
                databaseList.map{ entity ->
                    InputData(
                        entity.mood,
                        entity.rating,
                        entity.date
                    )
                }.also{mappedList ->
                    entries.clear()
                    entries.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        findViewById<Button>(R.id.submit).setOnClickListener{
            val name = findViewById<EditText>(R.id.entryName).text.toString()
            val rating = findViewById<EditText>(R.id.entryRating).text.toString()
            val date = findViewById<EditText>(R.id.entryDate).text.toString()

            findViewById<EditText>(R.id.entryName).text.clear()
            findViewById<EditText>(R.id.entryRating).text.clear()
            findViewById<EditText>(R.id.entryDate).text.clear()

            val newEntry = InputData(name, rating, date)
            entries.add(newEntry)

            lifecycleScope.launch(IO){
                (application as ArticleApplication).db.inputDao().insertEntry(
                InputEntry(
                    mood = newEntry.mood,
                    rating = newEntry.rating,
                    date = newEntry.date
                )
            )
            }
            adapter.notifyDataSetChanged()
        }
    }
}