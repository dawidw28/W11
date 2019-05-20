package com.example.w11

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ConsultationDAO {

    @Query("select * from Consultation")
    fun getAll() : List<Consultation>

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    fun insert (consultation: Consultation)

    @Query("delete from Consultation where Consultation.name like :givenName")
    fun delete (givenName: String)
}