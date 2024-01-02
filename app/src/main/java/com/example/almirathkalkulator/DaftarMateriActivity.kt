package com.example.almirathkalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.almirathkalkulator.databinding.ActivityDaftarMateriBinding
import com.example.almirathkalkulator.materi.MateriActivity

class DaftarMateriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarMateriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        title.text = "Materi"

        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE


        val intent = Intent(this, MateriActivity::class.java)
        binding.materiPertama.setOnClickListener{
            intent.putExtra("materi", "materi1")
            startActivity(intent)
        }
        binding.materiKedua.setOnClickListener{
            intent.putExtra("materi", "materi2")
            startActivity(intent)
        }
        binding.materiKetiga.setOnClickListener{
            intent.putExtra("materi", "materi3")
            startActivity(intent)
        }
        binding.materiKeempat.setOnClickListener{
            intent.putExtra("materi", "materi4")
            startActivity(intent)
        }
        binding.materiKelima.setOnClickListener{
            intent.putExtra("materi", "materi5")
            startActivity(intent)
        }
        binding.materiKeenam.setOnClickListener{
            intent.putExtra("materi", "materi6")
            startActivity(intent)
        }
        binding.materiKetujuh.setOnClickListener{
            intent.putExtra("materi", "materi7")
            startActivity(intent)
        }
        binding.materiKedelapan.setOnClickListener{
            intent.putExtra("materi", "materi8")
            startActivity(intent)
        }

    }
}