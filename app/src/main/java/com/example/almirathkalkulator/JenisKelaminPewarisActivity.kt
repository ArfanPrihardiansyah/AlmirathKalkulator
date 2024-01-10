package com.example.almirathkalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.almirathkalkulator.data_waris.AppDatabase
import com.example.almirathkalkulator.data_waris.WarisDataDao
import com.example.almirathkalkulator.data_waris.WarisUtamaActivity
import com.example.almirathkalkulator.databinding.ActivityJenisKelaminPewarisBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JenisKelaminPewarisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJenisKelaminPewarisBinding
    private lateinit var warisDataDao: WarisDataDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJenisKelaminPewarisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        val buttonBack: ImageView = findViewById(R.id.btn_back)
        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        val rgJenisKelaminPewaris: RadioGroup = findViewById(R.id.rg_jenisKelaminPewaris)
        buttonDelete.visibility = View.GONE

        title.text = "Jenis Kelamin"

        val harta = intent.getLongExtra("harta", 0)
        val hutang = intent.getLongExtra("hutang", 0)
        val wasiat = intent.getLongExtra("wasiat", 0)
        val biayaPerawatanJenazah = intent.getIntExtra("biayaPerawatanJenazah", 0)

        warisDataDao = AppDatabase.getDatabase(this).warisDataDao()
        checkIfDatabaseNotEmpty()

        binding.btnLanjut.setOnClickListener {
            val intent = Intent(this, WarisUtamaActivity::class.java)
            intent.putExtra("harta", harta)
            intent.putExtra("hutang", hutang)
            intent.putExtra("wasiat", wasiat)
            intent.putExtra("biayaPerawatanJenazah", biayaPerawatanJenazah)
            val selectedRadioButtonId = rgJenisKelaminPewaris.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Silakan pilih Jenis Kelamin Pewaris!", Toast.LENGTH_SHORT).show()
            } else {
                val jenisKelaminPewaris: String = when (selectedRadioButtonId) {
                    R.id.rb_laki -> "Laki-Laki"
                    R.id.rb_perempuan -> "Perempuan"
                    else -> "Laki-Laki"
                }

                intent.putExtra("jenisKelaminPewaris", jenisKelaminPewaris)
                startActivity(intent)

            }


        }
        buttonBack.setOnClickListener {
            finish()
        }
    }
    private fun checkIfDatabaseNotEmpty() {
        lifecycleScope.launch {
            val isDatabaseNotEmpty = withContext(Dispatchers.IO) {
                warisDataDao.isDataExist() > 0
            }

            if (isDatabaseNotEmpty) {
                displayWarisData()
            } else {
                Log.d("YourActivity", "Database is empty.")
            }
        }
    }

    private fun displayWarisData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val loadedWarisData = warisDataDao.getAllWarisData()
            var jenisKelamin = ""

            for (warisData in loadedWarisData) {
                jenisKelamin = warisData.jenisKelamin
            }
            withContext(Dispatchers.Main) {
                if (jenisKelamin == "Laki-Laki") {
                    binding.rbLaki.isChecked = true
                } else if (jenisKelamin == "Perempuan") {
                    binding.rbPerempuan.isChecked = true
                }
            }
        }
    }
}