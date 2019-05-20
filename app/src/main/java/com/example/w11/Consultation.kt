package com.example.w11

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Consultation")
data class Consultation (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "name") val name : String,
    @ColumnInfo(name= "room") val room : String,
    @ColumnInfo(name= "date") val date : String,
    @ColumnInfo(name= "time") var time : String
)