package com.example.appclima_nikognzlz.repositorio

import  com.example.appclima_nikognzlz.repositorio.model.Ciudad
import  com.example.appclima_nikognzlz.repositorio.model.Clima
import  com.example.appclima_nikognzlz.repositorio.model.ListForecast

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun traerClima(lat: Float, lon: Float) : Clima
    suspend fun traerPronostico(nombre: String) : List<ListForecast>
}