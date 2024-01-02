package com.example.almirathkalkulator

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WarisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ahliWarisTextView: TextView = itemView.findViewById(R.id.txt_ahliWaris)
    val bagianTextView: TextView = itemView.findViewById(R.id.txt_bagian)
    val jumlahOrangTextView: TextView = itemView.findViewById(R.id.txt_jumlahOrang)
    val hartaPerOrangTextView: TextView = itemView.findViewById(R.id.txt_hartaPerOrang)
    val hartaBagian: TextView = itemView.findViewById(R.id.txt_hartaBagian)
    val keterangan: TextView = itemView.findViewById(R.id.txt_keterangan)
}
