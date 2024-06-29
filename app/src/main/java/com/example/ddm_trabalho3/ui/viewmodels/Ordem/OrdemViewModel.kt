package com.example.ddm_trabalho3.ui.viewmodels.Ordem

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ddm_trabalho3.data.AppDatabase
import com.example.ddm_trabalho3.domain.Maquina
import com.example.ddm_trabalho3.domain.Ordem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class OrdemViewModel(context: Context) : ViewModel() {

    private val _listaOrdens = MutableStateFlow<List<Ordem>>(emptyList())
    val listaOrdens: StateFlow<List<Ordem>> get() = _listaOrdens

    private val _listaMaquinas = MutableStateFlow<List<Maquina>>(emptyList())
    val listaMaquinas: StateFlow<List<Maquina>> get() = _listaMaquinas


    private val ordemDao = AppDatabase.getInstance(context).ordemDao()
    private val maquinaDao = AppDatabase.getInstance(context).maquinaDao()


    init {
        viewModelScope.launch {
            ordemDao.getTodasOrdensServico().asFlow().distinctUntilChanged().collect { ordens ->
                _listaOrdens.value = ordens
            }
        }

        viewModelScope.launch {
            maquinaDao.getTodasMaquinas().asFlow().distinctUntilChanged().collect { maquinas ->
                _listaMaquinas.value = maquinas
            }
        }
    }

    fun cadastrarOrdem(descricao: String, maqtag: String, status: Boolean, tipo: String) {
        val ordem = Ordem(descricao, maqtag, status, tipo)

        ordemDao.inserirOrdemServico(ordem)
    }

}