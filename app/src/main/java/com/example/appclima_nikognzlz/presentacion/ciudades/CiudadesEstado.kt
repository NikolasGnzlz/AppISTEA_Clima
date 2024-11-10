package com.example.appclima_nikognzlz.presentacion.ciudades

import com.example.appclima_nikognzlz.repositorio.model.Ciudad

sealed class CiudadesEstado {
    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado( val ciudades : List<Ciudad> ) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()
}