package com.comida.repartidog.ui.usuario


import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
data class pedido(
    var nombre: String? = "",
    var cantidad:String? ="",
    var descripcion: String="",
    var precio:String? ="",
    var precioenvio:String? ="",
    var email: String? ="",

   var total: String? =""

)