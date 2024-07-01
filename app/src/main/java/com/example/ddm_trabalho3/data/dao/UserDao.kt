package com.example.ddm_trabalho3.data.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ddm_trabalho3.domain.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirUsuario(usuario: User)

    @Query("DELETE FROM tbusuarios WHERE codigo = :usuarioId")
    fun excluirUsuarioPorId(usuarioId: Int)

    @Update
    fun atualizarUsuario(usuario: User)

    @Query("SELECT * FROM tbusuarios ORDER BY nome ASC")
    fun getTodosUsuarios(): LiveData<List<User>>

    @Query("SELECT * FROM tbusuarios WHERE codigo = :usuarioId")
    fun getUsuarioPorId(usuarioId: Int): LiveData<User>

    @Query("SELECT * FROM tbusuarios WHERE email = :usuarioEmail")
    fun getUsuarioPorEmail(usuarioEmail: String): LiveData<User>
}