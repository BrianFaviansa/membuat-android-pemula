package com.faviansa.submissionandroidpemula

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar_musician)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val detailData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Musician>("EXTRA_MUSICIAN", Musician::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Musician>("EXTRA_MUSICIAN")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivPhotoDetail: ImageView = findViewById(R.id.detail_photo)
        val tvNameDetail: TextView = findViewById(R.id.detail_tvName)
        val tvDescDetail: TextView = findViewById(R.id.detail_tvDesc)
        val tvFavSongDetail: TextView = findViewById(R.id.detail_tvFavSong)

        detailData?.let {
            ivPhotoDetail.setImageResource(it.photo)
            tvNameDetail.text = it.name
            tvDescDetail.text = it.description
            tvFavSongDetail.text = it.favSong
        }

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener {
            val contentForShare = """
        Check out my favorite musician :
        Name : ${detailData?.name}
        Description : ${detailData?.description}
        My Favorite Song : ${detailData?.favSong}
    """.trimIndent()
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check this out!")
                putExtra(Intent.EXTRA_TEXT, contentForShare)
            }
            val chooser = Intent.createChooser(shareIntent, "Share via")
            startActivity(chooser)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val aboutPageIntent = Intent(this@DetailActivity, AboutActivity::class.java)
                val aboutData = About(
                    "Brian Faviansa Putra Diasti",
                    "brianfpd31@gmail.com",
                    R.drawable.photo_about

                )
                aboutPageIntent.putExtra(AboutActivity.EXTRA_ABOUT, aboutData)
                startActivity(aboutPageIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}