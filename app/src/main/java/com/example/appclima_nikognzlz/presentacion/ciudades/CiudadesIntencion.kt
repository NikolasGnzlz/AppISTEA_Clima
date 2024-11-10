package com.example.appclima_nikognzlz.presentacion.ciudades

import  com.example.appclima_nikognzlz.repositorio.model.Ciudad

sealed class CiudadesIntencion {
    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val ciudad: Ciudad) : CiudadesIntencion()
}