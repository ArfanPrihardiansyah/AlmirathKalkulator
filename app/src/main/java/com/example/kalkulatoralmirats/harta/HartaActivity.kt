package com.example.kalkulatoralmirats.harta

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kalkulatoralmirats.MainActivity
import com.example.kalkulatoralmirats.data_waris.AppDatabase
import com.example.kalkulatoralmirats.R
import com.example.kalkulatoralmirats.data_waris.WarisDataDao
import com.example.kalkulatoralmirats.databinding.ActivityHartaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class HartaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHartaBinding
    private lateinit var warisDataDao: WarisDataDao

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHartaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        val buttonBack: ImageView = findViewById(R.id.btn_back)
        val buttonDelete: ImageView = findViewById(R.id.btn_delete)

        title.text = "Harta"

        if (binding.inputHarta.text.isNullOrEmpty() || binding.inputHutang.text.isNullOrEmpty()
            || binding.inputWasiat.text.isNullOrEmpty() || binding.inputBiayaPerawatanJenazah.text.isNullOrEmpty()){
            buttonDelete.visibility = View.GONE
        }

        editTextToTextView(binding.inputHarta, binding.numberHartaPewaris)
        editTextToTextView(binding.inputBiayaPerawatanJenazah, binding.numberBiayaPerawatanJenazah)
        editTextToTextView(binding.inputWasiat, binding.numberWasiat)
        editTextToTextView(binding.inputHutang, binding.numberHutang)

        warisDataDao = AppDatabase.getDatabase(this).warisDataDao()
        checkIfDatabaseNotEmpty()

        binding.inputHarta.addTextChangedListener(getTextWatcher(binding.inputHarta, binding.inputHutang, binding.inputWasiat,binding.inputBiayaPerawatanJenazah, buttonDelete))
        binding.inputHutang.addTextChangedListener(getTextWatcher(binding.inputHarta, binding.inputHutang, binding.inputWasiat,binding.inputBiayaPerawatanJenazah, buttonDelete))
        binding.inputWasiat.addTextChangedListener(getTextWatcher(binding.inputHarta, binding.inputHutang, binding.inputWasiat,binding.inputBiayaPerawatanJenazah, buttonDelete))
        binding.inputBiayaPerawatanJenazah.addTextChangedListener(getTextWatcher(binding.inputHarta, binding.inputHutang, binding.inputWasiat,binding.inputBiayaPerawatanJenazah, buttonDelete))

        binding.btnLanjut.setOnClickListener {
            val harta = binding.inputHarta.text.toString().toLongOrNull() ?: 0
            val biayaPerawatanJenazah = binding.inputBiayaPerawatanJenazah.text.toString().toLongOrNull() ?: 0
            val wasiat = binding.inputWasiat.text.toString().toLongOrNull() ?: 0
            val hutang = binding.inputHutang.text.toString().toLongOrNull() ?: 0
            val hartaBersih = harta - hutang - wasiat - biayaPerawatanJenazah
            if (hartaBersih <= 0 || harta <= hutang || wasiat >= ((1.0/3.0) * (harta-hutang))){
                if(hartaBersih <= 0){
                    Toast.makeText(this, "Harta habis!", Toast.LENGTH_SHORT).show()
                }else if (wasiat > 0){
                    if (wasiat >= ((1.0/3.0) * (harta-hutang))) {
                        Toast.makeText(this, "Wasiat tidak boleh lebih dari 1/3 setelah Harta dikurang dengan Hutang!", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                val intent = Intent(this, TableHartaActivity::class.java)
                intent.putExtra("harta", harta)
                intent.putExtra("hutang", hutang)
                intent.putExtra("wasiat", wasiat)
                intent.putExtra("biayaPerawatanJenazah", biayaPerawatanJenazah)
                startActivity(intent)
            }
        }
        buttonBack.setOnClickListener {
            finish()
        }
        buttonDelete.setOnClickListener {
            val nilaiKosong: Editable = Editable.Factory.getInstance().newEditable("")
            binding.inputHarta.text = nilaiKosong
            binding.inputHutang.text = nilaiKosong
            binding.inputWasiat.text = nilaiKosong
            binding.inputBiayaPerawatanJenazah.text = nilaiKosong
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
        GlobalScope.launch(Dispatchers.IO) {
            val loadedWarisData = warisDataDao.getAllWarisData()

            var harta = ""
            var hutang = ""
            var wasiat = ""
            var biayaPerawatanJenazah = ""


            for (warisData in loadedWarisData) {
                harta = warisData.harta.toString()
                hutang = warisData.hutang.toString()
                wasiat = warisData.wasiat.toString()
                biayaPerawatanJenazah = warisData.biayaPerawatanJenazah.toString()
            }
            val hartaEditable: Editable = Editable.Factory.getInstance().newEditable(if(harta == "0") "" else harta)
            val hutangEditable: Editable = Editable.Factory.getInstance().newEditable(if(hutang == "0") "" else hutang)
            val wasiatEditable: Editable = Editable.Factory.getInstance().newEditable(if(wasiat == "0") "" else wasiat)
            val biayaPerawatanJenazahEditable: Editable = Editable.Factory.getInstance().newEditable(if(biayaPerawatanJenazah == "0") "" else biayaPerawatanJenazah)

            binding.inputHarta.text = hartaEditable
            binding.inputHutang.text = hutangEditable
            binding.inputWasiat.text = wasiatEditable
            binding.inputBiayaPerawatanJenazah.text = biayaPerawatanJenazahEditable

        }
    }
    private fun editTextToTextView(editText: EditText, textView: TextView){
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputText: String = s.toString()
                if(inputText.isNullOrEmpty()){
                    textView.text = "Rp. 0"
                }else{
                    textView.text = convertToRupiahFormat(inputText.toDouble())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            fun convertToRupiahFormat(angka: Double): String {
                val localeID = Locale("id", "ID")
                val currencyFormat = NumberFormat.getCurrencyInstance(localeID)

                currencyFormat.currency = Currency.getInstance("IDR")
                currencyFormat.maximumFractionDigits = 0

                return "Rp. " + currencyFormat.format(angka).substring(2)
            }
        })
    }

    private fun getTextWatcher(
        editText1: EditText,
        editText2: EditText,
        editText3: EditText,
        editText4: EditText,
        button: ImageView
    ): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

            }

            override fun afterTextChanged(editable: Editable?) {
                lifecycleScope.launch(Dispatchers.Main) {
                    if (editText1.text.isNullOrEmpty() &&
                        editText2.text.isNullOrEmpty() &&
                        editText3.text.isNullOrEmpty() &&
                        editText4.text.isNullOrEmpty()
                    ) {
                        button.visibility = View.INVISIBLE
                    } else {
                        button.visibility = View.VISIBLE
                    }
                }
            }
        }

    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}