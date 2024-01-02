package com.example.almirathkalkulator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TableHartaZoomFragment : Fragment() {
    private var harta: String = ""
    private var hutang: String = ""
    private var wasiat: String = ""
    private var biayaPerawatanJenazah: String = ""
    private var hartaBersih: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table_harta_zoom, container, false)
        val angkaHarta: TextView = view.findViewById(R.id.hartaAwal)
        val angkaHutang: TextView = view.findViewById(R.id.hutang)
        val angkaWasiat: TextView = view.findViewById(R.id.wasiat)
        val angkaBiayaPerawatanJenazah: TextView = view.findViewById(R.id.biayaPerawatanJenazah)
        val angkaHartaBersih: TextView = view.findViewById(R.id.txt_totalHartaBersih)
        angkaHarta.text = harta
        angkaHutang.text = hutang
        angkaWasiat.text = wasiat
        angkaBiayaPerawatanJenazah.text = biayaPerawatanJenazah
        angkaHartaBersih.text = hartaBersih
        return view

    }
    fun setData(harta: String, hutang: String, wasiat: String, biayaPerawatanJenazah: String, hartaBersih: String) {
        this.harta = harta
        this.hutang = hutang
        this.wasiat = wasiat
        this.biayaPerawatanJenazah = biayaPerawatanJenazah
        this.hartaBersih = hartaBersih
    }


}
