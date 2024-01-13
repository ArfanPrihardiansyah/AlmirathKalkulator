package com.example.kalkulatoralmirats.data_waris

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kalkulatoralmirats.R

class WarisAdapterZoom(private val dataList: ArrayList<WarisData>) : RecyclerView.Adapter<WarisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_hasil_zoom_recycle_view, parent, false)
        return WarisViewHolder(view)
    }

    override fun onBindViewHolder(holder: WarisViewHolder, position: Int) {
        val data = dataList[position]
        holder.ahliWarisTextView.text = data.ahliWaris
        holder.bagianTextView.text = data.bagian
        holder.jumlahOrangTextView.text = data.jumlahOrang.toString()
        holder.hartaPerOrangTextView.text = data.hartaPerOrang
        holder.hartaBagian.text = data.hartaBagian
        holder.keterangan.text = data.keterangan
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}