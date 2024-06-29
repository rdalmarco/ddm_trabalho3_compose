package com.example.ddm_trabalho3.ui.viewmodels.Maquina

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MaquinaViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaquinaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MaquinaViewModel(context) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
    }
}