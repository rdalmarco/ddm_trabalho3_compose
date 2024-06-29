package com.example.ddm_trabalho3.ui.viewmodels.Maquina

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.ddm_trabalho3.data.AppDatabase
import com.example.ddm_trabalho3.data.api.RetrofitInstance
import com.example.ddm_trabalho3.domain.Maquina
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MaquinaViewModel(private val context: Context) : ViewModel() {

    private val _listaMaquinas = MutableStateFlow<List<Maquina>>(emptyList())
    val listaMaquinas: StateFlow<List<Maquina>> get() = _listaMaquinas
    private val maquinaDao = AppDatabase.getInstance(context).maquinaDao()

    init {
        fetchMaquinas()

        // Carregar máquinas do DAO na inicialização
        viewModelScope.launch {
            maquinaDao.getTodasMaquinas().asFlow().distinctUntilChanged().collect { maquinas ->
                _listaMaquinas.value = maquinas
            }
        }
    }

    fun fetchMaquinas() {
        RetrofitInstance.api.getMaquinas().enqueue(object : Callback<List<Maquina>> {
            override fun onResponse(call: Call<List<Maquina>>, response: Response<List<Maquina>>) {
                if (response.isSuccessful) {
                    val maquinas = response.body() ?: emptyList()
                    // Atualizar StateFlow e inserir no DAO
                    viewModelScope.launch {
                        _listaMaquinas.value = maquinas
                        maquinas.forEach { maquina ->
                            maquinaDao.inserirMaquina(maquina)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Maquina>>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }

    fun cadastrarMaquina(codigo: String, modelo: String, observacao: String, status: Boolean) {
        val maquina = Maquina(codigo, modelo, observacao, status)

        viewModelScope.launch {
            // Inserir a nova máquina no DAO
            maquinaDao.inserirMaquina(maquina)

            // Atualizar o StateFlow com a lista atualizada do DAO
            maquinaDao.getTodasMaquinas().asFlow().distinctUntilChanged().collect { maquinas ->
                _listaMaquinas.value = maquinas
            }

        }
    }
}
