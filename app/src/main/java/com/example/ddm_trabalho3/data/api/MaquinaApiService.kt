package com.example.ddm_trabalho3.data.api


import com.example.ddm_trabalho3.domain.Maquina
import retrofit2.Call
import retrofit2.http.GET

interface MaquinaApiService {

    @GET("/api/mhu/machine/mobile")
    fun getMaquinas(): Call<List<Maquina>>
}