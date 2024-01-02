package com.example.almirathkalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.almirathkalkulator.databinding.ActivityDaftarTutorialBinding
import com.example.almirathkalkulator.databinding.ActivityTutorialBinding
import com.example.almirathkalkulator.materi.MateriActivity
import com.example.almirathkalkulator.tutorial.TutorialActivity

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
    }
}