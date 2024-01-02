package com.example.almirathkalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.almirathkalkulator.databinding.ActivityTentangaplikasiBinding

class TentangaplikasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTentangaplikasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTentangaplikasiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        title.text = "Tentang Aplikasi"

        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

    }
}