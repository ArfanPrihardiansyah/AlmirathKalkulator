package com.example.almirathkalkulator.materi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.almirathkalkulator.harta.HartaActivity
import com.example.almirathkalkulator.R
import com.example.almirathkalkulator.databinding.ActivityMateriBinding

class MateriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMateriBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        title.text = "Materi"

        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        val isiMateri = intent.getStringExtra("materi")
        if (isiMateri.equals("materi1")){
            val fragment = Materi1Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi2")){
            val fragment = Materi2Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi3")){
            val fragment = Materi3Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi4")){
            val fragment = Materi4Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi5")){
            val fragment = Materi5Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi6")){
            val fragment = Materi6Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi7")){
            val fragment = Materi7Fragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("materi8")){
            val fragment = Materi8Fragment()
            loadFragment(fragment)
        }




    }
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flMateri, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, DaftarMateriActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}