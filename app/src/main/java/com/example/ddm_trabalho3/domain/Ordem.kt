package com.example.ddm_trabalho3.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tbordem",
    foreignKeys = [
        ForeignKey(entity = Maquina::class,
            parentColumns = ["tag"],
            childColumns = ["tag"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class Ordem(
    @ColumnInfo(name = "orddescricao") val descriao: String,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "ordstatus") val status: Boolean,
    @ColumnInfo(name = "ordtipo") val tipo: String,
    @ColumnInfo(name = "orddata") val dataCriacao: Long = Date().time
) {
    @PrimaryKey(autoGenerate = true) var codigo: Int = 0
}