package com.example.ddm_trabalho3.data.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ddm_trabalho3.domain.Ordem

@Dao
interface OrdemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirOrdemServico(ordemServico: Ordem)

    @Query("DELETE FROM tbordem WHERE codigo = :ordemServicoId")
    fun excluirOrdemServicoPorId(ordemServicoId: Int)

    @Query("SELECT * FROM tbordem ORDER BY codigo ASC")
    fun getTodasOrdensServico(): LiveData<List<Ordem>>

    @Query("SELECT * FROM tbordem WHERE codigo = :ordemServicoId")
    fun getOrdemServicoPorId(ordemServicoId: Int): LiveData<Ordem>

}