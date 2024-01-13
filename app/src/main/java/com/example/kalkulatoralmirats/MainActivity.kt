package com.example.kalkulatoralmirats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kalkulatoralmirats.harta.HartaActivity
import com.example.kalkulatoralmirats.materi.DaftarMateriActivity
import com.example.kalkulatoralmirats.tutorial.DaftarTutorialActivity
import com.example.kalkulatoralmirats.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKalkulator.setOnClickListener{
            val intent = Intent(this, HartaActivity::class.java)
            startActivity(intent)
        }
        binding.btnMateri.setOnClickListener{
            val intent = Intent(this, DaftarMateriActivity::class.java)
            startActivity(intent)
        }
        binding.btnTentangAplikasi.setOnClickListener{
            val intent = Intent(this, TentangaplikasiActivity::class.java)
            startActivity(intent)
        }
        binding.btnUmpanBalik.setOnClickListener{
            val intent = Intent(this, UmpanbalikActivity::class.java)
            startActivity(intent)
        }
        binding.btnTutorial.setOnClickListener{
            val intent = Intent(this, DaftarTutorialActivity::class.java)
            startActivity(intent)
        }
    }
}