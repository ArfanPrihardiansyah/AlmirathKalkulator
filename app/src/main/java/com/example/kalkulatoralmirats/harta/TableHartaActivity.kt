package com.example.kalkulatoralmirats.harta

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
import com.example.kalkulatoralmirats.ConvertToRupiah
import com.example.kalkulatoralmirats.JenisKelaminPewarisActivity
import com.example.kalkulatoralmirats.R
import com.example.kalkulatoralmirats.databinding.ActivityTableHartaBinding

class TableHartaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTableHartaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableHartaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        val buttonBack: ImageView = findViewById(R.id.btn_back)
        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        title.text = "Harta"

        binding.fabZoomIn.visibility = View.VISIBLE
        binding.fabZoomOut.visibility = View.GONE

        var harta = intent.getLongExtra("harta", 0)
        val hutang = intent.getLongExtra("hutang", 0)
        val wasiat = intent.getLongExtra("wasiat", 0)
        val biayaPerawatanJenazah = intent.getLongExtra("biayaPerawatanJenazah",0)

        val angkaHarta = ConvertToRupiah().convertToRupiahFormat(harta.toDouble())
        val angkaHutang = ConvertToRupiah().convertToRupiahFormat(hutang.toDouble())
        val angkaWasiat = ConvertToRupiah().convertToRupiahFormat(wasiat.toDouble())
        val angkaBiayaPerawatanJenazah = ConvertToRupiah().convertToRupiahFormat(biayaPerawatanJenazah.toDouble())
        val angkaHartaBersih = ConvertToRupiah().convertToRupiahFormat((harta - hutang - wasiat - biayaPerawatanJenazah).toDouble())

        val fragmentDefault = TableHartaDefaultFragment()
        fragmentDefault.setData(angkaHarta, angkaHutang, angkaWasiat, angkaBiayaPerawatanJenazah, angkaHartaBersih)
        loadFragment(fragmentDefault)

        binding.fabZoomIn.setOnClickListener {
            val fragment = TableHartaZoomFragment()
            fragment.setData(angkaHarta, angkaHutang, angkaWasiat, angkaBiayaPerawatanJenazah, angkaHartaBersih)
            loadFragment(fragment)
            binding.fabZoomIn.visibility = View.GONE
            binding.fabZoomOut.visibility = View.VISIBLE
        }
        binding.fabZoomOut.setOnClickListener {
            val fragment = TableHartaDefaultFragment()
            fragment.setData(angkaHarta, angkaHutang, angkaWasiat, angkaBiayaPerawatanJenazah, angkaHartaBersih)
            loadFragment(fragment)
            binding.fabZoomIn.visibility = View.VISIBLE
            binding.fabZoomOut.visibility = View.GONE
        }
        binding.btnLanjut.setOnClickListener {
            val intent = Intent(this, JenisKelaminPewarisActivity::class.java)
            intent.putExtra("harta", harta)
            intent.putExtra("hutang", hutang)
            intent.putExtra("wasiat", wasiat)
            intent.putExtra("biayaPerawatanJenazah", biayaPerawatanJenazah)
            startActivity(intent)
        }
        buttonBack.setOnClickListener {
            finish()
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, HartaActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flHarta, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}