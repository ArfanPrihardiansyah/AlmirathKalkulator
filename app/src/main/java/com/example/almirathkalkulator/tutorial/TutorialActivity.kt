package com.example.almirathkalkulator.tutorial

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.almirathkalkulator.harta.HartaActivity
import com.example.almirathkalkulator.R
import com.example.almirathkalkulator.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)


        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        val isiMateri = intent.getStringExtra("tutorial")
        if (isiMateri.equals("tutorial1")){
            title.text = "Tutorial Fitur Kalkulator"
            val fragment = TutorialKalkulatorFragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("tutorial2")){
            title.text = "Tutorial Fitur Materi"
            val fragment = TutorialMateriFragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("tutorial3")){
            title.text = "Tutorial Fitur Umpan Balik"
            val fragment = TutorialUmpanbalikFragment()
            loadFragment(fragment)
        }else if (isiMateri.equals("tutorial4")){
            title.text = "Tutorial Fitur Tentang"
            val fragment = TutorialTentangFragment()
            loadFragment(fragment)
        }




    }
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flTutorial, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, HartaActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}