package com.comida.repartidog.ui.comida

import java.io.Serializable

class ListaComida:Serializable {
    private var imagen: Int? = null
    private var nombre_comida: String? = null
    private var precio: String? = null
    private var precio_envio: String? = null
    private var tiempo_envio: String? = null
    private var descripcion: String? = null

    constructor(
        imagen: Int?,
        nombre_comida: String?,
        precio: String?,
        precio_envio: String?,
        tiempo_envio: String?,
        descripcion: String?
    ) {
        this.imagen = imagen
        this.nombre_comida = nombre_comida
        this.precio = precio
        this.precio_envio = precio_envio
        this.tiempo_envio = tiempo_envio
        this.descripcion = descripcion
    }

    fun getImagen(): Int? {
        return imagen
    }

    fun getDescripcion(): String? {
        return return descripcion
    }

    fun getNombre_comida(): String? {
        return nombre_comida
    }

    fun getTiempo_envio(): String? {
        return tiempo_envio
    }

    fun getPrecio(): String? {
        return precio
    }

    fun getPrecio_envio(): String? {
        return precio_envio
    }

}