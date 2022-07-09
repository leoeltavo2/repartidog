package com.comida.repartidog

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_A.setOnClickListener { menu() }
        button_R.setOnClickListener { registrar() }
    }

    private fun menu(){

        if (text_emai.text.isNotEmpty() && pass.text.isNotEmpty()){

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(text_emai.text.toString(),pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this,MenuActivity::class.java).apply {
                            putExtra("email", text_emai.text.toString()?:"")

                        }
                        startActivity(intent)
                          finish()
                    }else{
                        alert()
                    }
                }
        }else{
            alertt()

        }



    }

    private fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error al iniciar sesion")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
    private fun alertt(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Debes llenar los campos para  iniciar sesion")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
    private fun registrar(){

        val intent = Intent(this,RegisActivity::class.java)
        startActivity(intent)

    }

}