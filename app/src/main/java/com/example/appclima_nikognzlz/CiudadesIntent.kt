package com.example.appclima_nikognzlz

sealed class CiudadesIntent {
    object CargaCiudad : CiudadesIntent()
    data class SeleccionaCiudad(val nombreCiudad: String) : CiudadesIntent()
    object BuscaCiudadPorLocalicacion : CiudadesIntent()
}



