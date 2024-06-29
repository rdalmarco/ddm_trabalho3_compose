package com.example.ddm_trabalho3.ui.viewmodels.Login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.ddm_trabalho3.ui.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginViewModel(private val context : Context) : ViewModel() {

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> get() = _showToastEvent

    private val _userLoggedInEvent = MutableLiveData<Event<Boolean>>()
    val userLoggedInEvent: LiveData<Event<Boolean>> get() = _userLoggedInEvent

    fun login(email: String, senha: String): Boolean {
        val task = Firebase
            .auth
            .signInWithEmailAndPassword(email, senha)

        var isSuccess = false

        task.addOnSuccessListener {
            _showToastEvent.value = Event("Usuário logado com sucesso!")
            _userLoggedInEvent.value = Event(true)
            isSuccess = true
        }

        task.addOnFailureListener {
            Log.e(this.javaClass.simpleName, "login: ", it)
            _showToastEvent.value = Event("Erro na tentativa de login. " + it.message)
            _userLoggedInEvent.value = Event(false)
            isSuccess = false
        }

        return isSuccess
    }
}
