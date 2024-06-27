package com.example.ddm_trabalho3.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbmaquina")
data class Maquina(

    @PrimaryKey val tag: String,
    @ColumnInfo(name = "maqmodelo") val modelo: String,
    @ColumnInfo(name = "maqobservacao") val observacao: String,
    @ColumnInfo(name = "maqstatus") val status: Boolean
) {
}