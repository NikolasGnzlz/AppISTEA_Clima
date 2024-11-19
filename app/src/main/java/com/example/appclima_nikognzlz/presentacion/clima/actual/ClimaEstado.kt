package com.example.appclima_nikognzlz.presentacion.clima.actual

sealed class ClimaEstado {
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val principal: String= "",
        val descripcion: String= "",
        val st :Double = 0.0,
    ) : ClimaEstado()

    data class Error(
        val mensaje :String = "",
    ) : ClimaEstado()

    data object Vacio: ClimaEstado()

    data object Cargando: ClimaEstado()

}
