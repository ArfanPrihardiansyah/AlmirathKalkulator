package com.example.almirathkalkulator

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
import com.example.almirathkalkulator.databinding.ActivityHasilKalkulatorBinding
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class HasilKalkulatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilKalkulatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilKalkulatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title: TextView = findViewById(R.id.txt_title)
        val buttonBack: ImageView = findViewById(R.id.btn_back)
        val buttonDelete: ImageView = findViewById(R.id.btn_delete)
        buttonDelete.visibility = View.GONE

        title.text = "Pembagian"

        val hutang = intent.getLongExtra("hutang", 0).toDouble()
        val wasiat = intent.getLongExtra("wasiat", 0).toDouble()
        val biayaPerawatanJenazah = intent.getLongExtra("biayaPerawatanJenazah",0).toDouble()
        val totalHarta = intent.getLongExtra("harta", 0).toDouble()
        val harta = (totalHarta - (hutang + biayaPerawatanJenazah + wasiat))


        val jenisKelaminPewaris = intent.getStringExtra("jenisKelaminPewaris")
        val adaAyah = intent.getBooleanExtra("ayah", false)
        val adaIbu = intent.getBooleanExtra("ibu", false)
        val adaSuami = intent.getBooleanExtra("suami", false)
        val istri = intent.getIntExtra("istri", 0)
        val anakLaki = intent.getIntExtra("anakLaki", 0)
        val anakPerempuan = intent.getIntExtra("anakPerempuan", 0)



        binding.fabZoomIn.visibility = View.VISIBLE
        binding.fabZoomOut.visibility = View.GONE



        var keterangan = ""

        var bagianIbu = ""
        var bagianAyah = ""
        var bagianSuami = ""
        var bagianIstri = ""
        var bagianAnakPerempuan = ""
        var bagianAnakLaki = ""

        var hartaAyah = 0.0
        var hartaIbu = 0.0
        var hartaSuami = 0.0
        var hartaIstri = 0.0
        var hartaAnakLaki = 0.0
        var hartaAnakPerempuan = 0.0

        var sisaHarta = 0.0

        if(!adaSuami && istri == 0){
            if (!adaAyah && !adaIbu) {
                if (anakPerempuan == 0) {
                    if (anakLaki >= 1) {
                        bagianAnakLaki = "Ashobah"
                        hartaAnakLaki = harta
                    }
                } else if (anakPerempuan >= 1) {
                    if (anakLaki == 0) {
                        bagianAnakPerempuan = if(anakPerempuan == 1) "1/2 → 1/1" else "2/3 → 1/1"
                        hartaAnakPerempuan = harta
                        keterangan = "Radd"
                    } else if (anakLaki >= 1) {
                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"
                        hartaAnakLaki = harta * (anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble()
                        hartaAnakPerempuan = (harta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble()))
                    }

                }
            }else if (!adaAyah && adaIbu){
                if (anakLaki == 0) {
                    if (anakPerempuan == 0) {
                        bagianIbu = "1/3 → 1/1"
                        hartaIbu = harta
                    }else if (anakPerempuan == 1) {
                        bagianIbu = "1/6 → 1/4"
                        bagianAnakPerempuan = "1/2 → 3/4"
                        hartaIbu = harta * (1.0/4.0)
                        sisaHarta = harta - hartaIbu
                        hartaAnakPerempuan = sisaHarta
                        keterangan = "Radd"
                    }else if (anakPerempuan > 1){
                        bagianIbu = "1/6 → 1/5"
                        bagianAnakPerempuan = "2/3 → 4/5"
                        hartaIbu = harta * (1.0 / 5.0)
                        sisaHarta = harta - hartaIbu
                        hartaAnakPerempuan = sisaHarta
                        keterangan = "Radd"
                    }

                }else if (anakLaki >= 1) {
                    bagianIbu = "1/6 → 1/6"
                    hartaIbu = harta * (1.0/6.0)
                    sisaHarta = harta - hartaIbu
                    if (anakPerempuan == 0) {
                        bagianAnakLaki = "Ashobah"
                        hartaAnakLaki = sisaHarta
                    }else if (anakPerempuan >= 1) {
                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"
                        hartaAnakLaki = sisaHarta * (anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble()
                        hartaAnakPerempuan = sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble())
                    }
                }
            }else if (adaAyah && !adaIbu) {
                if (anakLaki == 0) {
                    if (anakPerempuan == 0){
                        bagianAyah = "Ashobah"
                        hartaAyah = harta
                    }else if (anakPerempuan == 1) {
                        hartaAnakPerempuan = harta*(1.0/2.0)
                        hartaAyah = harta - hartaAnakPerempuan
                        bagianAyah = "1/6+Ashobah → 1/4"
                        bagianAnakPerempuan = "1/2 → 3/4"
                    }else if (anakPerempuan > 1) {
                        hartaAnakPerempuan = harta*(2.0/3.0)
                        hartaAyah = harta - hartaAnakPerempuan
                        bagianAyah = "1/6+Ashobah → 1/5"
                        bagianAnakPerempuan = "2/3 → 4/5"
                    }
                }else if (anakLaki >= 1){
                    bagianAyah = "1/6 → 1/6"
                    hartaAyah = harta * (1.0/6.0)
                    sisaHarta = harta - hartaAyah
                    if (anakPerempuan == 0) {
                        bagianAnakLaki = "Ashobah"
                        hartaAnakLaki = sisaHarta
                    }else if (anakPerempuan >= 1) {
                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"
                        hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                        hartaAnakPerempuan = sisaHarta * (anakPerempuan/(anakLaki + anakLaki + anakPerempuan).toDouble())
                    }
                }
            }else if (adaAyah && adaIbu){
                hartaAyah = harta*(1.0/6.0)
                hartaIbu = harta*(1.0/6.0)
                if (anakLaki == 0) {
                    if (anakPerempuan == 0) {
                        bagianAyah = "Ashobah"
                        bagianIbu = "1/3 → 1/3"
                        hartaAyah = harta * (2.0 / 3.0)
                        hartaIbu = harta * (1.0 / 3.0)
                    }else if (anakPerempuan >= 1) {
                        if (anakPerempuan == 1) {
                            bagianAnakPerempuan = "1/2 → 3/5"
                            bagianIbu = "1/6 → 1/5"
                            bagianAyah = "1/6+Ashobah → 1/5"
                            hartaAnakPerempuan = harta*(1.0/2.0)
                            sisaHarta = harta - (hartaIbu + hartaAnakPerempuan + hartaAyah)
                            hartaAyah += sisaHarta

                        }else if (anakPerempuan > 1) {
                            bagianAnakPerempuan = "2/3 → 4/6"
                            bagianIbu = "1/6 → 1/6"
                            bagianAyah = "1/6+Ashobah → 1/6"
                            hartaAnakPerempuan = (harta*(2.0/3.0))
                        }
                    }
                }else if (anakLaki >= 1) {
                    bagianAyah = "1/6 → 1/6"
                    bagianIbu = "1/6 → 1/6"
                    if (anakPerempuan == 0) {
                        bagianAnakLaki = "Ashobah"
                        hartaAnakLaki = harta * (4.0/6.0)

                    }else if(anakPerempuan >= 1) {
                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"
                        sisaHarta = harta - (harta * (1.0/6.0) * 2)
                        hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                        hartaAnakPerempuan = sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble())
                    }
                }
            }
        }else if(adaSuami || istri > 0){
            if (!adaAyah && !adaIbu) {
                if (anakPerempuan == 0) {
                    if (anakLaki == 0) {
                        bagianIstri = "1/4 → 1/4"
                        bagianSuami = "1/2 → 1/2"

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            hartaIstri = harta * (1.0/4.0)
                            sisaHarta = harta - hartaIstri

                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            hartaSuami = harta * (1.0/2.0)
                            sisaHarta = harta - hartaSuami
                        }


                    } else if (anakLaki >= 1) {
                        bagianSuami = "1/4 → 1/4"
                        bagianIstri = "1/8 → 1/8"
                        bagianAnakLaki = "Ashobah"
                        hartaSuami = harta*(1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            hartaAnakLaki = harta*(7.0/8.0)
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            hartaAnakLaki = harta*(3.0/4.0)
                        }
                    }
                } else if (anakPerempuan >= 1) {
                    if (anakLaki == 0) {
                        bagianSuami = "1/4 → 1/4"
                        bagianIstri = "1/8 → 1/8"
                        hartaSuami = harta*(1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            hartaAnakPerempuan = harta*(7.0/8.0)
                            bagianAnakPerempuan = if (anakPerempuan > 1) "2/3 → 7/8" else "1/2 → 7/8"
                            keterangan = "Radd"
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            hartaAnakPerempuan = harta*(3.0/4.0)
                            bagianAnakPerempuan = if (anakPerempuan > 1) "2/3 → 3/4" else "1/2 → 3/4"
                            keterangan = "Radd"
                        }

                    } else if (anakLaki >= 1) {
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta * (1.0/8.0)
                        bagianSuami = "1/4 → 1/4"
                        bagianIstri = "1/8 → 1/8"
                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            sisaHarta = harta - hartaIstri
                            hartaAnakPerempuan = (sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble()))
                            hartaAnakLaki = (sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble()))
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            sisaHarta = harta - hartaSuami
                            hartaAnakPerempuan = (sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble()))
                            hartaAnakLaki = (sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble()))
                        }
                    }
                }
            }else if (!adaAyah && adaIbu){
                if (anakLaki == 0) {
                    if (anakPerempuan == 0) {
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIbu = "1/3 → 3/4"
                            bagianIstri = "1/4 → 1/4"
                            hartaIbu = harta*(3.0/4.0)
                            hartaIstri = harta*(1.0/4.0)
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianSuami = "1/2 → 1/2"
                            bagianIbu = "1/3 → 1/2"
                            hartaSuami = harta*(1.0/2.0)
                            hartaIbu = harta*(1.0/2.0)
                        }
                    }else if (anakPerempuan == 1) {
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIbu = "1/6 → 7/32"
                            bagianIstri = "1/8 → 1/8"
                            bagianAnakPerempuan = "1/2 → 21/32"
                            hartaIstri = harta*(1.0/8.0)
                            sisaHarta = harta - hartaIstri
                            hartaIbu = sisaHarta * (1.0/4.0)
                            hartaAnakPerempuan = sisaHarta*(3.0/4.0)
                            keterangan = "Radd"
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianIbu = "1/6 → 3/16"
                            bagianSuami = "1/4 → 1/4"
                            bagianAnakPerempuan = "1/6 → 3/16"
                            hartaSuami = harta * (1.0/4.0)
                            sisaHarta = harta - hartaSuami
                            hartaIbu = sisaHarta * (1.0/4.0)
                            hartaAnakPerempuan = sisaHarta*(3.0/4.0)
                            keterangan = "Radd"
                        }

                    }else if (anakPerempuan > 1){
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIbu = "1/6 → 7/40"
                            bagianIstri = "1/8 → 1/8"
                            bagianAnakPerempuan = "2/3 → 7/10"
                            hartaIstri = harta*(1.0/8.0)
                            sisaHarta = harta - hartaIstri
                            hartaIbu = sisaHarta * (1.0/5.0)
                            hartaAnakPerempuan = sisaHarta*(4.0/5.0)
                            keterangan = "Radd"
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianIbu = "1/6 → 2/13"
                            bagianSuami = "1/4 → 3/13"
                            bagianAnakPerempuan = "2/3 → 8/13"
                            hartaSuami = harta *(3.0/13.0)
                            hartaIbu = harta * (2.0/13.0)
                            hartaAnakPerempuan = harta*(8.0/13.0)
                            keterangan = "Aul"
                        }

                    }
                }else if (anakLaki >= 1) {
                    // Ada Ibu, ada suami/istri, ada anak laki
                    if (anakPerempuan == 0) {
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)
                        hartaIbu = harta * (1.0/6.0)

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIbu = "1/6 → 4/24"
                            bagianIstri = "1/8 → 3/24"
                            bagianAnakLaki = "Ashobah"
                            sisaHarta = harta - (hartaIstri + hartaIbu)
                            hartaAnakLaki = sisaHarta
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianIbu = "1/6 → 2/12"
                            bagianSuami = "1/4 → 3/12"
                            bagianAnakLaki = "Ashobah"
                            sisaHarta = harta - (hartaSuami + hartaIbu)
                            hartaAnakLaki = sisaHarta
                        }
                    }else if (anakPerempuan >= 1) {
                        hartaIbu = harta * (1.0/6.0)
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)

                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIstri = "1/8 → 3/24"
                            bagianIbu = "1/6 → 4/24"
                            sisaHarta = harta - (hartaIstri + hartaIbu)
                            hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                            hartaAnakPerempuan = sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble())
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianIbu = "1/6 → 2/12"
                            bagianSuami = "1/4 → 3/12"
                            sisaHarta = harta - (hartaSuami + hartaIbu)
                            hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                            hartaAnakPerempuan = sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble())
                        }
                    }
                }
            }else if (adaAyah && !adaIbu){
                if (anakLaki == 0) {
                    if (anakPerempuan == 0) {
                        hartaIstri = harta * (1.0 / 4.0)
                        hartaSuami = harta * (1.0 / 2.0)
                        bagianAyah = "Ashobah"
                        bagianSuami = "1/2 → 1/2"
                        bagianIstri = "1/4 → 1/4"
                        if (jenisKelaminPewaris.equals("Laki-Laki")) {
                            hartaAyah = harta*(3.0/4.0)
                        } else if (jenisKelaminPewaris.equals("Perempuan")) {
                            hartaAyah = harta*(1.0/2.0)
                        }
                    }else if (anakPerempuan == 1) {
                        hartaAyah = harta*(1.0/6.0)
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)
                        hartaAnakPerempuan = harta*(1.0/2.0)

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianAyah = "1/6+Ashobah → 7/32"
                            bagianIstri = "1/8 → 1/8"
                            bagianAnakPerempuan = "1/2 → 21/32"
                            sisaHarta = harta - (hartaIstri+hartaAnakPerempuan+hartaAyah)
                            hartaAyah += sisaHarta
                        } else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianAyah = "1/6+Ashobah → 3/16"
                            bagianSuami = "1/4 → 1/4"
                            bagianAnakPerempuan = "1/2 → 9/16"
                            sisaHarta = harta - (hartaSuami+hartaAnakPerempuan + hartaAyah)
                            hartaAyah += sisaHarta
                        }
                    }else if (anakPerempuan > 1){
                        hartaAnakPerempuan = harta*(8.0/13.0)

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianAyah = "1/6+Ashobah → 7/40"
                            bagianIstri = "1/8 → 1/8"
                            bagianAnakPerempuan = "2/3 → 7/10"
                            hartaIstri = harta*(1.0/8.0)
                            hartaAyah = harta*(1.0/6.0)
                            hartaAnakPerempuan = harta*(2.0/3.0)
                            sisaHarta = harta - (hartaIstri+hartaAyah+hartaAnakPerempuan)
                            hartaAyah += sisaHarta
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianAyah = "1/6+Ashobah → 2/13"
                            bagianSuami = "1/4 → 3/13"
                            bagianAnakPerempuan = "2/3 → 8/13"
                            hartaAyah = harta*(2.0/13.0)
                            hartaSuami = harta *(3.0/13.0)
                            keterangan = "Aul"
                        }

                    }
                }else if (anakLaki >= 1) {
                    // Ada Ayah, ada suami/istri, ada anak laki
                    if (anakPerempuan == 0) {
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)
                        hartaAyah = harta * (1.0/6.0)

                        bagianAnakLaki = "Ashobah"

                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianAyah = "1/6 → 4/24"
                            bagianIstri = "1/8 → 3/24"
                            sisaHarta = harta - (hartaIstri + hartaAyah)
                            hartaAnakLaki = sisaHarta
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianAyah = "1/6 → 2/12"
                            bagianSuami = "1/4 → 3/12"
                            sisaHarta = harta - (hartaSuami + hartaAyah)
                            hartaAnakLaki = sisaHarta
                        }
                    }else if (anakPerempuan >= 1) {
                        hartaAyah = harta * (1.0/6.0)
                        hartaSuami = harta * (1.0/4.0)
                        hartaIstri = harta*(1.0/8.0)




                        bagianAnakLaki = "Ashobah"
                        bagianAnakPerempuan = "Ashobah-Bil-Ghoir"


                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianAyah = "1/6 → 4/24"
                            bagianIstri = "1/8 → 3/24"
                            sisaHarta = harta - (hartaIstri + hartaAyah)
                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianAyah = "1/6 → 2/12"
                            bagianSuami = "1/4 → 3/12"
                            sisaHarta = harta - (hartaSuami + hartaAyah)
                        }
                        hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                        hartaAnakPerempuan = sisaHarta * (anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble())
                    }
                }
            }else if (adaAyah && adaIbu){
                if (anakLaki == 0) {
                    if (anakPerempuan == 0) {
                        hartaAyah = harta * (2.0 / 6.0)
                        hartaSuami = harta *(1.0/2.0)
                        hartaIstri = harta*(1.0/4.0)
                        bagianAyah = "Ashobah"
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            bagianIbu = "U"
                            bagianIstri = "1/4 → 1/4"
                            hartaIbu = harta*(1.0/4.0)
                            sisaHarta = harta - (hartaAyah + hartaIbu + hartaIstri)
                            hartaAyah += sisaHarta

                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            bagianIbu = "U"
                            bagianSuami = "1/2 → 1/2"
                            hartaIbu = harta*(1.0/6.0)
                        }
                    }else if (anakPerempuan >= 1) {
                        if (anakPerempuan == 1) {
                            if (jenisKelaminPewaris.equals("Laki-Laki")){
                                hartaAyah = harta*(1.0/6.0)
                                hartaIbu = harta*(1.0/6.0)
                                hartaIstri = harta*(1.0/8.0)
                                hartaAnakPerempuan = harta*(1.0/2.0)
                                bagianAyah = "1/6+Ashobah → 7/40"
                                bagianIbu = "1/6 → 7/40"
                                bagianIstri ="1/8 → 1/8"
                                bagianAnakPerempuan = "1/2 → 21/40"
                                sisaHarta = harta - (hartaAyah + hartaIbu + hartaIstri + hartaAnakPerempuan)
                                hartaAyah += sisaHarta
                            }else if(jenisKelaminPewaris.equals("Perempuan")){
                                hartaAyah = harta*(2.0/13.0)
                                hartaIbu = harta*(2.0/13.0)
                                hartaSuami = harta*(3.0/13.0)
                                hartaAnakPerempuan = harta*(6.0/13.0)
                                bagianAyah = "1/6+Ashobah → 2/13"
                                bagianIbu = "1/6 → 2/13"
                                bagianSuami = "1/4 → 3/13"
                                bagianAnakPerempuan = "1/2 → 6/13"
                                keterangan = "Aul"
                            }
                        }else if (anakPerempuan > 1) {
                            if (jenisKelaminPewaris.equals("Laki-Laki")){
                                hartaAyah = harta*(4.0/27.0)
                                hartaIbu = harta*(4.0/27.0)
                                hartaIstri = harta*(3.0/27.0)
                                hartaAnakPerempuan = harta*(16.0/27.0)
                                bagianAyah = "1/6+Ashobah → 4/27"
                                bagianIbu = "1/6 → 4/27"
                                bagianIstri ="1/8 → 3/27"
                                bagianAnakPerempuan = "2/3 → 16/27"

                                sisaHarta = harta - (hartaAyah + hartaIbu + hartaIstri + hartaAnakPerempuan)
                                hartaAyah += sisaHarta
                                keterangan = "Aul"
                            }else if(jenisKelaminPewaris.equals("Perempuan")){
                                hartaAyah = harta*(2.0/15.0)
                                hartaIbu = harta*(2.0/15.0)
                                hartaSuami = harta*(3.0/15.0)
                                hartaAnakPerempuan = harta*(8.0/15.0)
                                bagianAyah = "2/15+Ashobah → 2/15"
                                bagianIbu = "1/6 → 2/15"
                                bagianSuami ="1/4 → 3/15"
                                bagianAnakPerempuan = "2/3 → 8/15"
                                keterangan = "Aul"
                            }
                        }
                    }
                }else if (anakLaki >= 1) {
                    if (anakPerempuan == 0) {
                        if (jenisKelaminPewaris.equals("Laki-Laki")){
                            hartaIbu = harta*(1.0/6.0)
                            hartaAyah = harta * (1.0 / 6.0)
                            hartaIstri = harta *(1.0/8.0)
                            sisaHarta = harta - (hartaIbu + hartaAyah + hartaIstri)
                            hartaAnakLaki = sisaHarta
                            bagianAyah = "1/6 → 4/24"
                            bagianIbu = "1/6 → 4/24"
                            bagianIstri = "1/8 → 3/24"
                            bagianAnakLaki = "Ashobah"

                        }else if(jenisKelaminPewaris.equals("Perempuan")){
                            hartaIbu = harta*(1.0/6.0)
                            hartaAyah = harta * (1.0 / 6.0)
                            hartaSuami = harta *(1.0/4.0)
                            sisaHarta = harta - (hartaIbu + hartaAyah + hartaSuami)
                            hartaAnakLaki = sisaHarta
                            bagianAyah = "1/6 → 2/12"
                            bagianIbu = "1/6 → 2/12"
                            bagianSuami = "1/4 → 3/12"
                            bagianAnakLaki = "Ashobah"
                        }
                    }else if (anakPerempuan >= 1) {
                        if (anakPerempuan >= 1) {
                            bagianAyah = "1/6 → 4/24"
                            bagianIbu = "1/6 → 4/24"
                            bagianAnakLaki = "Ashobah"
                            bagianAnakPerempuan = "Ashobah-Bil-Ghoir"
                            hartaIbu = harta*(1.0/6.0)
                            hartaAyah = harta * (1.0 / 6.0)
                            if (jenisKelaminPewaris.equals("Laki-Laki")){
                                hartaIstri = harta*(1.0/8.0)
                                sisaHarta = harta - (hartaIbu + hartaAyah + hartaIstri)
                                bagianIstri ="1/8 → 3/24"

                            }else if(jenisKelaminPewaris.equals("Perempuan")){
                                hartaSuami = harta *(1.0/4.0)
                                sisaHarta = harta - (hartaIbu + hartaAyah + hartaSuami)
                                bagianSuami = "1/4 → 3/12"

                            }
                            hartaAnakLaki = sisaHarta * ((anakLaki + anakLaki) / (anakLaki + anakLaki + anakPerempuan).toDouble())
                            hartaAnakPerempuan = sisaHarta * anakPerempuan / (anakLaki + anakLaki + anakPerempuan).toDouble()
                        }
                    }
                }
            }
        }
        val data: ArrayList<WarisData> = ArrayList()

        if (adaIbu || adaAyah || adaSuami || istri >= 1 || anakPerempuan >= 1 || anakLaki >= 1){
            if (adaAyah) {
                data.add(WarisData("Ayah",bagianAyah, 1,ConvertToRupiah().convertToRupiahFormat(hartaAyah), ConvertToRupiah().convertToRupiahFormat(hartaAyah), keterangan))
            }
            if (adaIbu){
                data.add(WarisData("Ibu",bagianIbu, 1,ConvertToRupiah().convertToRupiahFormat(hartaIbu), ConvertToRupiah().convertToRupiahFormat(hartaIbu), keterangan))
            }
            if(jenisKelaminPewaris == "Laki-Laki"){
                if (istri >= 1) {
                    data.add(WarisData("Istri",bagianIstri, istri,ConvertToRupiah().convertToRupiahFormat(hartaIstri), ConvertToRupiah().convertToRupiahFormat(hartaIstri/istri), keterangan))
                }
            }else{
                if (adaSuami) {
                    data.add(WarisData("Suami",bagianSuami, 1,ConvertToRupiah().convertToRupiahFormat(hartaSuami), ConvertToRupiah().convertToRupiahFormat(hartaSuami), keterangan))
                }
            }
            if (anakLaki >= 1) {
                data.add(WarisData("Anak Laki-Laki",bagianAnakLaki, anakLaki,ConvertToRupiah().convertToRupiahFormat(hartaAnakLaki), ConvertToRupiah().convertToRupiahFormat(hartaAnakLaki/anakLaki), keterangan))
            }
            if (anakPerempuan >= 1) {
                data.add(WarisData("Anak Perempuan",bagianAnakPerempuan, anakPerempuan,ConvertToRupiah().convertToRupiahFormat(hartaAnakPerempuan), ConvertToRupiah().convertToRupiahFormat(hartaAnakPerempuan/anakPerempuan), keterangan))
            }

        }else{
            data.add(WarisData("Baitul Mal","1/1", 0,ConvertToRupiah().convertToRupiahFormat(harta), ConvertToRupiah().convertToRupiahFormat(harta), keterangan))
        }
        if (!adaIbu && !adaAyah && (adaSuami || istri >= 1) && anakPerempuan < 1 && anakLaki < 1){
            data.add(WarisData("Baitul Mal", "Ashobah", 0, ConvertToRupiah().convertToRupiahFormat(sisaHarta), ConvertToRupiah().convertToRupiahFormat(sisaHarta), keterangan))
        }


        val fragmentDefault = TablePembagianDefaultFragment()
        fragmentDefault.setData(data, ConvertToRupiah().convertToRupiahFormat(harta))
        loadFragment(fragmentDefault)

        val fragmentZoom = TablePembagianZoomFragment()
        fragmentZoom.setData(data, ConvertToRupiah().convertToRupiahFormat(harta))

        binding.fabZoomIn.setOnClickListener {
            loadFragment(fragmentZoom)
            binding.fabZoomIn.visibility = View.GONE
            binding.fabZoomOut.visibility = View.VISIBLE
        }
        binding.fabZoomOut.setOnClickListener {
            loadFragment(fragmentDefault)
            binding.fabZoomIn.visibility = View.VISIBLE
            binding.fabZoomOut.visibility = View.GONE
        }

        buttonBack.setOnClickListener {
            finish()
        }
        binding.btnHome.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, WarisUtamaActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flPembagian, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}