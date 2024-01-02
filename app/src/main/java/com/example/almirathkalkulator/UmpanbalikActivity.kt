package com.example.almirathkalkulator

import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.almirathkalkulator.databinding.ActivityUmpanbalikBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore

class UmpanbalikActivity : AppCompatActivity() {


    private lateinit var binding: ActivityUmpanbalikBinding
    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUmpanbalikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        title.text = "Umpan Balik"

        val buttonBack: ImageView = findViewById(R.id.btn_back)
        buttonBack.setOnClickListener {
            finish()
        }

        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        val rgPilihSebagai: RadioGroup = findViewById(R.id.rg_pilihSebagai)
        val db = Firebase.firestore

        var data = HashMap<String, String>()
        binding.inputEmail.visibility = View.GONE

        binding.rbAnonymous.setOnClickListener {
            binding.inputEmail.visibility = View.GONE

        }
        binding.rbEmail.setOnClickListener {
            binding.inputEmail.visibility = View.VISIBLE
        }


        binding.btnLanjut.setOnClickListener {
            val selectedRadioButtonId = rgPilihSebagai.checkedRadioButtonId
            if (selectedRadioButtonId == -1 || binding.inputUmpanBalik.text.toString().isEmpty()) {
                Toast.makeText(this, "Tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show()
            } else {
                if (isConnectedToInternet()) {
                    when (selectedRadioButtonId) {
                        R.id.rb_anonymous -> {
                            data = hashMapOf(
                                "sebagai" to "anonymous",
                                "masukan" to binding.inputUmpanBalik.text.toString()
                            )
                            db.collection("umpanBalik")
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    val alertDialogBuilder = AlertDialog.Builder(this)

                                    alertDialogBuilder.setTitle("Perhatian")
                                    alertDialogBuilder.setMessage("Terima Kasih atas masukannya!")

                                    alertDialogBuilder.setPositiveButton("Oke") { dialog, which ->
                                        finish()
                                    }

                                    val alertDialog = alertDialogBuilder.create()
                                    alertDialog.show()

                                }.addOnFailureListener { e ->
                                    Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
                                }

                        }
                        R.id.rb_email -> {
                            if (binding.inputEmail.text.toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString().trim()).matches()) {
                                if (binding.inputEmail.text.toString().isEmpty()){
                                    Toast.makeText(this, "Email tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this, "Masukkan email dengan benar!", Toast.LENGTH_SHORT).show()
                                }

                            }else {
                                data = hashMapOf(
                                    "sebagai" to binding.inputEmail.text.toString(),
                                    "masukan" to binding.inputUmpanBalik.text.toString()
                                )
                                db.collection("umpanBalik")
                                    .add(data)
                                    .addOnSuccessListener { documentReference ->
                                        val alertDialogBuilder = AlertDialog.Builder(this)

                                        alertDialogBuilder.setTitle("Perhatian")
                                        alertDialogBuilder.setMessage("Terima Kasih atas masukannya!")

                                        alertDialogBuilder.setPositiveButton("Oke") { dialog, which ->
                                            finish()
                                        }

                                        val alertDialog = alertDialogBuilder.create()
                                        alertDialog.show()

                                    }.addOnFailureListener { e ->
                                        Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }

                        }
                    }
                } else {
                    val alertDialogBuilder = AlertDialog.Builder(this)

                    alertDialogBuilder.setTitle("Perhatian")
                    alertDialogBuilder.setMessage("Anda tidak terhubung ke Internet!")
                    alertDialogBuilder.setPositiveButton("Oke") { dialog, which ->
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()
                }

            }

        }

    }
}