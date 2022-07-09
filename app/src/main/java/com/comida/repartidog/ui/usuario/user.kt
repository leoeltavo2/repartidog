package com.comida.repartidog.ui.usuario


import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
data class user(
    var username: String? = "",
    var apellido: String? ="",
    var email: String? =""
)