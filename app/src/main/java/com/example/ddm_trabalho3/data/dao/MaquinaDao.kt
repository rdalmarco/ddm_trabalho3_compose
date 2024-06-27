package com.example.ddm_trabalho3.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ddm_trabalho3.domain.Maquina

@Dao
interface MaquinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirMaquina(maquina: Maquina)

    @Query("DELETE FROM tbmaquina WHERE tag = :maquinaTag")
    fun excluirMaquinaPorId(maquinaTag: String)

    @Query("SELECT * FROM tbmaquina ORDER BY tag ASC")
    fun getTodasMaquinas(): LiveData<List<Maquina>>

    @Query("SELECT * FROM tbmaquina WHERE tag = :maquinaTag")
    fun getMaquinaPorId(maquinaTag: String): LiveData<Maquina>
}

