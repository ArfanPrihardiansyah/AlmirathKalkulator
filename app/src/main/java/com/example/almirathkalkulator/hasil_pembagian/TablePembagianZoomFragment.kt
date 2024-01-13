package com.example.almirathkalkulator.hasil_pembagian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.almirathkalkulator.R
import com.example.almirathkalkulator.data_waris.WarisAdapterZoom
import com.example.almirathkalkulator.data_waris.WarisData

class TablePembagianZoomFragment : Fragment() {
    private var data: ArrayList<WarisData> = ArrayList()
    private var totalPembagianHarta = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table_pembagian_zoom, container, false)
        val totalPembagian: TextView = view.findViewById(R.id.txt_totalPembagianHarta)
        totalPembagian.text = totalPembagianHarta
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_tableHasil)
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager
        val adapter = WarisAdapterZoom(data)
        recyclerView.adapter = adapter
        return view
    }
    fun setData(data: ArrayList<WarisData>, totalPembagianHarta: String){
        this.data = data
        this.totalPembagianHarta = totalPembagianHarta
    }
}