package com.example.ddm_trabalho3.ui.viewmodels.Perfil

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ddm_trabalho3.data.AppDatabase
import com.example.ddm_trabalho3.data.dao.UserDao
import com.example.ddm_trabalho3.domain.User
import com.example.ddm_trabalho3.ui.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class PerfilViewModel(private val context: Context) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val userDao: UserDao = AppDatabase.getInstance(context).userDao()

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> get() = _showToastEvent

    private val _userCreatedEvent = MutableLiveData<Event<Boolean>>()
    val userCreatedEvent: LiveData<Event<Boolean>> get() = _userCreatedEvent

    private val _userLoadedEvent = MutableLiveData<Event<User>>()
    val userLoadedEvent: LiveData<Event<User>> get() = _userLoadedEvent

    private val _userUpdatedEvent = MutableLiveData<Event<Boolean>>()
    val userUpdatedEvent: LiveData<Event<Boolean>> get() = _userUpdatedEvent

    fun cadastrarUser(nome: String, email: String, senha: String, onResult: (Boolean, String) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = User(nome, email, senha)
                    val userDao = AppDatabase.getInstance(context).userDao()
                    userDao.inserirUsuario(user)
                    onResult(true, "Usuário cadastrado com sucesso!")
                } else {
                    onResult(false, "Erro ao cadastrar usuário. ${task.exception?.message}")
                }
            }
    }

    fun loadUser() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            val email = user.email ?: ""
            val userDao: UserDao = AppDatabase.getInstance(context).userDao()

            userDao.getUsuarioPorEmail(email).observeForever { userFromDb ->
                userFromDb?.let {
                    _userLoadedEvent.value = Event(it)
                }
            }
        } else {
            _showToastEvent.value = Event("Usuário não está logado")
        }
    }

    fun salvarAlteracoes(nome: String, email: String, senha: String) {
        val user = Firebase.auth.currentUser
        if (user != null) {

            // Atualiza a senha se necessário
            if (senha.isNotEmpty()) {
                user.updatePassword(senha)
                    .addOnSuccessListener {
                        // Atualização no Firebase Authentication bem-sucedida
                        _showToastEvent.value = Event("Senha atualizada com sucesso!")
                    }
                    .addOnFailureListener { e ->
                        // Tratamento de erro ao atualizar a senha
                        _showToastEvent.value = Event("Erro ao atualizar senha: ${e.message}")
                    }
            }

            // Atualiza o perfil localmente (Room)
            viewModelScope.launch {
                val userLiveData = userDao.getUsuarioPorEmail(user.email ?: "")
                userLiveData.observeForever { userFromDb ->
                    userFromDb?.let {
                        it.nome = nome
                        it.email = email
                        viewModelScope.launch {
                            userDao.atualizarUsuario(it)
                            _userUpdatedEvent.value = Event(true)
                        }
                    }
                    // Importante remover o observer após o uso para evitar vazamentos de memória
                    userLiveData.removeObserver {}
                }
            }
        } else {
            _showToastEvent.value = Event("Usuário não está logado")
        }
    }


    fun excluirUsuario(onComplete: () -> Unit) {
        val user = Firebase.auth.currentUser
        user?.delete()
            ?.addOnSuccessListener {
                onComplete()
            }
            ?.addOnFailureListener { e ->
                Log.e(TAG, "Erro ao excluir usuário do Firebase: ${e.message}", e)
                _showToastEvent.value = Event("Erro ao excluir usuário: ${e.message}")
            }
    }

    companion object {
        private const val TAG = "PerfilViewModel"
    }

}