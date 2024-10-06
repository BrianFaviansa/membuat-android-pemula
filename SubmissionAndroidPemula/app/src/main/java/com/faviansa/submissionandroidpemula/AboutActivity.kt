package com.faviansa.submissionandroidpemula

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutActivity : AppCompatActivity() {
    private lateinit var tvAboutName: TextView
    private lateinit var tvAboutEmail: TextView
    private lateinit var imageAbout: ImageView

    companion object {
        const val EXTRA_ABOUT = "extra_about"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        tvAboutName = findViewById(R.id.tvAbout_name)
        tvAboutEmail = findViewById(R.id.tvAbout_email)
        imageAbout = findViewById(R.id.imageAbout)

        val toolbar: Toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val aboutData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<About>(EXTRA_ABOUT, About::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<About>(EXTRA_ABOUT)
        }

        if (aboutData != null) {
            tvAboutName.text = aboutData.name ?: ""
            tvAboutEmail.text = aboutData.email ?: ""
            aboutData.photo.let { photoResId ->
                imageAbout.setImageResource(photoResId)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}