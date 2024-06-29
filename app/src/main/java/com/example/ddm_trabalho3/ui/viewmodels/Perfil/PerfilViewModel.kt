package com.example.ddm_trabalho3.ui.viewmodels.Perfil

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ddm_trabalho3.data.AppDatabase
import com.example.ddm_trabalho3.data.dao.UserDao
import com.example.ddm_trabalho3.domain.User
import com.example.ddm_trabalho3.ui.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class PerfilViewModel(private val context : Context) : ViewModel(){

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> get() = _showToastEvent

    private val _userCreatedEvent = MutableLiveData<Event<Boolean>>()
    val userCreatedEvent: LiveData<Event<Boolean>> get() = _userCreatedEvent

    fun cadastrarUser(nome: String, email: String, senha: String): Boolean {
        val task = Firebase
            .auth
            .createUserWithEmailAndPassword(email, senha)

        var isSuccess = false

        task.addOnSuccessListener {
            _showToastEvent.value = Event("Usuário cadastrado com sucesso!")
            _userCreatedEvent.value = Event(true)
            isSuccess = true

            val user = User(nome, email, senha)
            val userDao = AppDatabase.getInstance(context).userDao()
            userDao.inserirUsuario(user)
        }

        task.addOnFailureListener {
            Log.e(this.javaClass.simpleName, "cadastrarUser: ", it)
            _showToastEvent.value = Event("Erro ao cadastrar usuário. " + it.message)
            _userCreatedEvent.value = Event(false)
            isSuccess = false
        }

        return isSuccess
    }

    fun excluirUser() {
        val task = Firebase
            .auth

        val user: FirebaseUser? = Firebase.auth.currentUser

        val userDao: UserDao = AppDatabase.getInstance(context).userDao()
        val userBanco: LiveData<User> = userDao.getUsuarioPorEmail(user?.email.toString())

        user?.let {
            it.delete()
                .addOnCompleteListener() {
                        task ->
                    if (task.isSuccessful) {
                        _showToastEvent.value = Event("Usuario excluído. ")
                        userBanco.value?.let { it1 -> userDao.excluirUsuarioPorId(it1.codigo) }
                    } else {
                        _showToastEvent.value = Event("Falha ao excluir ")
                    }
                }
        }
    }


    fun loadUser(): LiveData<User> {
        val task = Firebase
            .auth
            .currentUser

        val email = task?.email

        val userDao: UserDao = AppDatabase.getInstance(context).userDao()
        return userDao.getUsuarioPorEmail(email.toString())
    }

}