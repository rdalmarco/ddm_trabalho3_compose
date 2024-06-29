package com.example.ddm_trabalho3.ui.viewmodels.Ordem

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrdemViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrdemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrdemViewModel(context) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
    }

}