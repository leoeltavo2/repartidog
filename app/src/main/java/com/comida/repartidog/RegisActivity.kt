package com.comida.repartidog

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.comida.repartidog.ui.usuario.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        button_Regi.setOnClickListener {

            main()
            writeNewUser(text_nombre_usuario.text.toString(), text_apellido_usuario.text.toString(), text_emaill.text.toString())
        }

    }

    private fun main() {
        if (text_emaill.text.isNotEmpty() && text_password.text.isNotEmpty() && text_nombre_usuario.text.isNotEmpty() && text_apellido_usuario.text.isNotEmpty()) {

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    text_emaill.text.toString(),
                    text_password.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MenuActivity::class.java).apply {
                            putExtra("email", text_emaill.text.toString())


                        }
                        startActivity(intent)
                        finish()

                    } else {
                        alert()
                    }
                }
        }

    }

    private  fun writeNewUser(name:String, apellido:String, email:String){
        var mDatos = FirebaseDatabase.getInstance().getReference()
        mDatos.child("Usuario").push().setValue(user(name,apellido, email))
    }

    private fun alert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error al registrar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}