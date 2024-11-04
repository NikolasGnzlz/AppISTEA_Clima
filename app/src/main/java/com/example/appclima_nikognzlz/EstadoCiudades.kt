package com.example.appclima_nikognzlz

data class EstadoCiudades(
    val ciudades: List<String> = emptyList(),
    val ciudadSeleccionada: String? = null,
    val cargando: Boolean = false,
    val error: String? = null
)
