package com.example.kalkulatoralmirats.data_waris

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waris_data_table")
data class WarisDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val harta: Long,
    val hutang: Long,
    val wasiat: Long,
    val biayaPerawatanJenazah: Long,
    val jenisKelamin: String,
    val ayah: Boolean,
    val ibu: Boolean,
    val istri: Int,
    val suami: Boolean,
    val anakLaki: Int,
    val anakPerempuan: Int
)
