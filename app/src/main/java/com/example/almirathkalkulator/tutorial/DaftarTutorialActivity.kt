package com.example.almirathkalkulator.tutorial

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.almirathkalkulator.MainActivity
import com.example.almirathkalkulator.R
import com.example.almirathkalkulator.databinding.ActivityDaftarTutorialBinding
import com.example.almirathkalkulator.harta.HartaActivity

class DaftarTutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        title.text = "Tutorial"

        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        val intent = Intent(this, TutorialActivity::class.java)
        binding.tutorial1.setOnClickListener {
            intent.putExtra("tutorial", "tutorial1")
            startActivity(intent)
        }

        binding.tutorial2.setOnClickListener {
            intent.putExtra("tutorial", "tutorial2")
            startActivity(intent)
        }
        binding.tutorial3.setOnClickListener {
            intent.putExtra("tutorial", "tutorial3")
            startActivity(intent)
        }
        binding.tutorial4.setOnClickListener {
            intent.putExtra("tutorial", "tutorial4")
            startActivity(intent)
        }
        binding.tutorial5.setOnClickListener {
            openYouTubeVideo()
        }
    }
    fun openYouTubeVideo() {
        val videoId = "1qJlB50ZtoQ"

        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$videoId"))

        // Memeriksa apakah aplikasi YouTube terinstall
        if (intentApp.resolveActivity(packageManager) != null) {
            startActivity(intentApp)
        } else {
            // Jika tidak terinstall, buka YouTube melalui browser
            startActivity(intentWeb)
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}