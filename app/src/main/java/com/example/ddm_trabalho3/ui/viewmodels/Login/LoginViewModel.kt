package com.example.ddm_trabalho3.ui.viewmodels.Login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.ddm_trabalho3.ui.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth

class LoginViewModel(private val context: Context) : ViewModel() {

    private lateinit var auth: FirebaseAuth

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> get() = _showToastEvent

    private val _userLoggedInEvent = MutableLiveData<Event<Boolean>>()
    val userLoggedInEvent: LiveData<Event<Boolean>> get() = _userLoggedInEvent

    fun login(email: String, senha: String) {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener {
                _showToastEvent.value = Event("Usuário logado com sucesso!")
                _userLoggedInEvent.value = Event(true)
            }
            .addOnFailureListener { e ->
                Log.e(this.javaClass.simpleName, "login: ", e)
                val errorMessage = when (e) {
                    is FirebaseAuthInvalidCredentialsException -> "Credenciais inválidas. Verifique seu e-mail e senha."
                    else -> "Erro na tentativa de login. ${e.message}"
                }
                _showToastEvent.value = Event(errorMessage)
                _userLoggedInEvent.value = Event(false)
            }
    }
}