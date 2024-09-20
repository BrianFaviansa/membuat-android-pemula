package com.faviansa.submissionandroidpemula

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMusicians: RecyclerView
    private val list = ArrayList<Musician>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvMusicians = findViewById(R.id.rv_musicians)
        rvMusicians.setHasFixedSize(true)

        list.addAll(getListMusicians())
        showRecyclerList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val aboutPageIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutPageIntent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMusicians(): ArrayList<Musician> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMucisian = ArrayList<Musician>()
        for (i in dataName.indices) {
            val musician = Musician(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listMucisian.add(musician)
        }
        return listMucisian
    }

    private fun showRecyclerList() {
        rvMusicians.layoutManager = LinearLayoutManager(this)
        val listMusicianAdapter = ListMusicianAdapter(list)
        rvMusicians.adapter = listMusicianAdapter
    }
}