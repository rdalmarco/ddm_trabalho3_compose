package com.example.ddm_trabalho3.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbusuarios")
data class User(
    @ColumnInfo(name = "nome") var nome: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "senha") var senha: String,
) {
    @PrimaryKey(autoGenerate = true) var codigo: Int = 0
    //@ColumnInfo(name = "imagem_path") val imagemPath: String // Caminho da imagem
}